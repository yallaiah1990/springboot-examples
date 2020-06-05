package com.employeemngt.service.impl;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.Employee;
import com.employeemngt.repository.AddressRepository;
import com.employeemngt.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportServiceImplTest {

	@InjectMocks
	ReportServiceImpl reportServiceImpl;

	@Mock
	EmployeeRepository employeerepository;

	@Mock
	AddressRepository addressrepository;

	@Mock
	MockHttpServletResponse response;
	
	@Mock
	HttpServletResponse responseh;

	@Mock
	ServletOutputStream servletOutputStream;

	private Employee employee;
	private Address address;
	private Department department;

	@Before
	public void initialSetup() throws ParseException {
		employee = new Employee();
		employee.setId("12");
		employee.setFirstName("Vishnu");
		employee.setLastName("Vardan");
		employee.setGender("male");
		employee.setGmail("vishnu@gmail.com");
		employee.setPhoneNumber("9874563214");
		employee.setDateOfJoin(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"));
		employee.setDateOfBirth(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"));

		address = new Address();
		address.setCity("banglore");
		address.setCountry("india");
		address.setStreet("main road");
		address.setZipCode("560037");
		address.setAddType("Prasent");

		Address address2 = new Address();
		address2.setCity("banglore");
		address2.setCountry("india");
		address2.setStreet("main road");
		address2.setZipCode("560037");
		address2.setAddType("Residence");

		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		addresses.add(address2);

		department = new Department();
		department.setName("account");
		department.setDescription("account related");

		employee.setAddress(addresses);
		employee.setDepartment(department);
	}

	@Test
	public void downloadReportInPdfTest() throws IOException {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeerepository.findAll()).thenReturn(employees);
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		reportServiceImpl.downloadReportInPdf(response);
	}

	@Test
	public void downloadReportInCsvTest() throws IOException {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeerepository.findAll()).thenReturn(employees);
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		//reportServiceImpl.downloadReportInCsv(responseh);
	}

}
