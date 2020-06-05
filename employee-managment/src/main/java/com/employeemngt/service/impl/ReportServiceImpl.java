package com.employeemngt.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemngt.constant.EmployeeManagementConstants;
import com.employeemngt.model.Address;
import com.employeemngt.model.Employee;
import com.employeemngt.model.EmployeeCSVReportData;
import com.employeemngt.repository.AddressRepository;
import com.employeemngt.repository.EmployeeRepository;
import com.employeemngt.service.ReportService;
import com.employeemngt.util.CustomeMappingUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class ReportServiceImpl implements ReportService {

	private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	AddressRepository addressrepository;

	@Override
	public void downloadReportInExcel(HttpServletResponse response) {
		logger.info("downloadReportInExcel");
	}

	@Override
	public void downloadReportInPdf(HttpServletResponse response) {

		String[] headers = { EmployeeManagementConstants.EMPLOYEE_ID, EmployeeManagementConstants.EMPLOYEE_FIRSTNAME,
				EmployeeManagementConstants.EMPLOYEE_LASTNAME, EmployeeManagementConstants.EMPLOYEE_GMAIL,
				EmployeeManagementConstants.EMPLOYEE_GENDER, EmployeeManagementConstants.EMPLOYEE_DATEOFJOIN,
				EmployeeManagementConstants.EMPLOYEE_DATEOFBIRTH, EmployeeManagementConstants.EMPLOYEE_PHONENUMBER,
				EmployeeManagementConstants.EMPLOYEE_ADDRESS_PRESENT,
				EmployeeManagementConstants.EMPLOYEE_ADDRESS_RESIDENCE,
				EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_NAME,
				EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_DESCRIPTION };

		List<EmployeeCSVReportData> employees = seggrigateDataForReport(employeerepository.findAll());
		response.setContentType("text/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"employeeData.pdf\"");
		Document document = new Document();

		try {
			OutputStream outstream = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			document.open();
			document.addTitle("Employee Data");

			PdfPTable table = new PdfPTable(12); // 12 columns.
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setWidths(new float[] { 2f, 5f, 5f, 10f, 3f, 10f, 10f, 8f, 7f, 7f, 7f, 7f });

			for (String header : headers) {
				PdfPCell cell = new PdfPCell(new Paragraph(header, FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cell);
			}

			for (EmployeeCSVReportData employeeCSVReportData : employees) {
				PdfPCell id = new PdfPCell(
						new Paragraph(employeeCSVReportData.getId(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell firstName = new PdfPCell(new Paragraph(employeeCSVReportData.getFirstName(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell lastName = new PdfPCell(new Paragraph(employeeCSVReportData.getLastName(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell gmail = new PdfPCell(
						new Paragraph(employeeCSVReportData.getGmail(), FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell gender = new PdfPCell(new Paragraph(employeeCSVReportData.getGender(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell dateOfJoin = new PdfPCell(new Paragraph(employeeCSVReportData.getDateOfJoin().toString(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell dateOfBirth = new PdfPCell(new Paragraph(employeeCSVReportData.getDateOfBirth().toString(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell phoneNumber = new PdfPCell(new Paragraph(employeeCSVReportData.getPhoneNumber(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell addressPresent = new PdfPCell(new Paragraph(employeeCSVReportData.getAddressPresent(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell addressResidence = new PdfPCell(new Paragraph(employeeCSVReportData.getAddressResidence(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell departmentName = new PdfPCell(new Paragraph(employeeCSVReportData.getDepartmentName(),
						FontFactory.getFont(FontFactory.HELVETICA, 6)));
				PdfPCell departmentDescription = new PdfPCell(
						new Paragraph(employeeCSVReportData.getDepartmentDescription(),
								FontFactory.getFont(FontFactory.HELVETICA, 6)));

				table.addCell(id);
				table.addCell(firstName);
				table.addCell(lastName);
				table.addCell(gmail);
				table.addCell(gender);
				table.addCell(dateOfJoin);
				table.addCell(dateOfBirth);
				table.addCell(phoneNumber);
				table.addCell(addressPresent);
				table.addCell(addressResidence);
				table.addCell(departmentName);
				table.addCell(departmentDescription);
			}

			document.add(table);
			document.close();
			writer.close();
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void downloadReportInCsv(HttpServletResponse response) {

		CustomeMappingUtil<EmployeeCSVReportData> employeeData = new CustomeMappingUtil<>();
		List<EmployeeCSVReportData> employees = seggrigateDataForReport(employeerepository.findAll());

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"employeeData.csv\"");

		String[] columns = { EmployeeManagementConstants.EMPLOYEE_FIRSTNAME,
				EmployeeManagementConstants.EMPLOYEE_LASTNAME, EmployeeManagementConstants.EMPLOYEE_GMAIL,
				EmployeeManagementConstants.EMPLOYEE_GENDER, EmployeeManagementConstants.EMPLOYEE_DATEOFJOIN,
				EmployeeManagementConstants.EMPLOYEE_DATEOFBIRTH, EmployeeManagementConstants.EMPLOYEE_PHONENUMBER,
				EmployeeManagementConstants.EMPLOYEE_ADDRESS_PRESENT,
				EmployeeManagementConstants.EMPLOYEE_ADDRESS_RESIDENCE,
				EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_NAME,
				EmployeeManagementConstants.EMPLOYEE_DEPARTMENT_DESCRIPTION };

		employeeData.setColumnMapping(columns);
		employeeData.setType(EmployeeCSVReportData.class);

		try {
			StatefulBeanToCsv<EmployeeCSVReportData> btcsv = new StatefulBeanToCsvBuilder<EmployeeCSVReportData>(
					response.getWriter()).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(employeeData)
							.build();
			writeData(employees, btcsv);
		} catch (IOException e) {
			logger.info(e.getLocalizedMessage(), e);
		}
	}
	
	public void writeData(List<EmployeeCSVReportData> employees, StatefulBeanToCsv<EmployeeCSVReportData> btcsv) {
		try {
			btcsv.write(employees);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			logger.info(e.getLocalizedMessage(), e);
		}
	}

	public List<EmployeeCSVReportData> seggrigateDataForReport(List<Employee> employees) {

		List<EmployeeCSVReportData> employeecsvreportdata = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeCSVReportData employeecsvreport = new EmployeeCSVReportData();
			employeecsvreport.setId(employee.getId());
			employeecsvreport.setFirstName(employee.getFirstName());
			employeecsvreport.setLastName(employee.getLastName());
			employeecsvreport.setGmail(employee.getGmail());
			employeecsvreport.setGender(employee.getGender());
			employeecsvreport.setDateOfJoin(new Date(employee.getDateOfJoin().getTime()));
			employeecsvreport.setDateOfBirth(new Date(employee.getDateOfBirth().getTime()));
			employeecsvreport.setPhoneNumber(employee.getPhoneNumber());

			List<Address> addresses = employee.getAddress();
			for (Address address : addresses) {
				StringBuilder stringbf = new StringBuilder();
				if ("Prasent".equalsIgnoreCase(address.getAddType())) {
					stringbf.append(address.getStreet() + " ");
					stringbf.append(address.getCity() + " ");
					stringbf.append(address.getCountry() + " ");
					stringbf.append(address.getZipCode());

					employeecsvreport.setAddressPresent(stringbf.toString());
				} else {
					stringbf.append(address.getStreet() + " ");
					stringbf.append(address.getCity() + " ");
					stringbf.append(address.getCountry() + " ");
					stringbf.append(address.getZipCode());

					employeecsvreport.setAddressResidence(stringbf.toString());
				}
			}

			employeecsvreport.setDepartmentName(employee.getDepartment().getName());
			employeecsvreport.setDepartmentDescription(employee.getDepartment().getDescription());

			employeecsvreportdata.add(employeecsvreport);
		}
		return employeecsvreportdata;
	}

}
