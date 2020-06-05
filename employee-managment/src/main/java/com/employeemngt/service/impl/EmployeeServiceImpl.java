package com.employeemngt.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employeemngt.constant.EmployeeManagementConstants;
import com.employeemngt.model.Address;
import com.employeemngt.model.AddressHistory;
import com.employeemngt.model.Department;
import com.employeemngt.model.DepartmentHistory;
import com.employeemngt.model.Employee;
import com.employeemngt.model.EmployeeHistory;
import com.employeemngt.repository.AddressHistoryRepository;
import com.employeemngt.repository.AddressRepository;
import com.employeemngt.repository.DepartmentHistoryRepository;
import com.employeemngt.repository.DepartmentRepository;
import com.employeemngt.repository.EmployeeHistoryRepository;
import com.employeemngt.repository.EmployeeIdSequenceRepositoryImpl;
import com.employeemngt.repository.EmployeeRepository;
import com.employeemngt.service.EmployeeService;
import com.opencsv.CSVReader;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	AddressRepository addressrepository;

	@Autowired
	DepartmentRepository departmentrepository;

	@Autowired
	EmployeeHistoryRepository employeehistorypository;

	@Autowired
	AddressHistoryRepository addresshistoryrepository;

	@Autowired
	DepartmentHistoryRepository departmenthistoryrepository;

	@Autowired
	EmployeeIdSequenceRepositoryImpl employeeidSeq;

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setId(
				Long.toString(employeeidSeq.getNextSequenceId(EmployeeManagementConstants.EMPLOYEE_ID_SEQUENCE_NAME)));
		logger.info("adding employee address");
		List<Address> address = addressrepository.save(employee.getAddress());
		employee.setAddress(address);

		logger.info("adding employee department");
		Department department = departmentrepository.save(employee.getDepartment());
		employee.setDepartment(department);

		Employee employeeData = employeerepository.save(employee);

		return employeeData;
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return employeerepository.findOne(employeeId);
	}

	@Override
	public Employee editEmployeeDetails(Employee employee) {
		employeerepository.findOne(employee.getId());
		List<Address> address = employee.getAddress();
		Department department = employee.getDepartment();
		addressrepository.save(address);
		departmentrepository.save(department);
		return employeerepository.save(employee);
	}

	@Override
	public void deleteEmployeeByid(String employeeId) {
		logger.info("moving employee to history");
		Employee employee = employeerepository.findOne(employeeId);

		List<AddressHistory> addresshistory = new ArrayList<>();

		for (Address address : employee.getAddress()) {
			AddressHistory addrresshitry = new DozerBeanMapper().map(address, AddressHistory.class);
			addresshistory.add(addrresshitry);
		}

		DepartmentHistory departmenthistory = new DozerBeanMapper().map(employee.getDepartment(),
				DepartmentHistory.class);

		EmployeeHistory employeehistory = new DozerBeanMapper().map(employee, EmployeeHistory.class);

		movedataToHistory(employeehistory, addresshistory, departmenthistory);

		deleteEmployeeData(employee);
	}

	private void deleteEmployeeData(Employee employee) {
		addressrepository.delete(employee.getAddress());
		departmentrepository.delete(employee.getDepartment());
		employeerepository.delete(employee);
	}

	private void movedataToHistory(EmployeeHistory employee, List<AddressHistory> address,
			DepartmentHistory department) {
		addresshistoryrepository.save(address);
		departmenthistoryrepository.save(department);
		employeehistorypository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employee");
		return employeerepository.findAll();
	}

	@Override
	public List<Employee> addListOfEmployees(MultipartFile file) throws IOException, ParseException {

		Reader reader = new InputStreamReader(file.getInputStream());
		@SuppressWarnings("deprecation")
		CSVReader csvreader = new CSVReader(reader, ',');

		List<Employee> employees = new ArrayList<>();
		List<Employee> employeesResult = new ArrayList<>();
		csvreader.readNext();

		employees = readCsvFile(csvreader, employees);

		for (Employee employee : employees) {
			logger.info("adding employee address");
			List<Address> address = addressrepository.save(employee.getAddress());
			employee.setAddress(address);

			logger.info("adding employee department");
			Department department = departmentrepository.save(employee.getDepartment());
			employee.setDepartment(department);

			logger.info("adding employee");
			employeesResult.add(employeerepository.save(employee));
		}

		return employeesResult;

	}

	private List<Employee> readCsvFile(CSVReader csvreader, List<Employee> employees)
			throws IOException, ParseException {
		String[] record;
		while ((record = csvreader.readNext()) != null) {
			Employee emp = new Employee();
			emp.setId(Long
					.toString(employeeidSeq.getNextSequenceId(EmployeeManagementConstants.EMPLOYEE_ID_SEQUENCE_NAME)));
			emp.setFirstName(record[0]);
			emp.setLastName(record[1]);
			emp.setGmail(record[2]);
			emp.setGender(record[3]);
			emp.setDateOfJoin(new SimpleDateFormat("dd/MM/yyyy").parse(record[4]));
			emp.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(record[5]));
			emp.setPhoneNumber(record[6]);

			List<Address> addresses = new ArrayList<>();

			String[] splt7 = record[7].split(",");
			if (!(splt7[0].isEmpty())) {
				System.out.println(splt7.length);
				Address address = new Address();
				address.setStreet(splt7[0]);
				address.setCity(splt7[1]);
				address.setCountry(splt7[2]);
				address.setAddType("Present");
				address.setZipCode(splt7[3]);
				addresses.add(address);
			}
			String[] splt8 = record[8].split(",");
			if (!(splt8[0].isEmpty())) {
				Address address2 = new Address();
				address2.setStreet(splt8[0]);
				address2.setCity(splt8[1]);
				address2.setCountry(splt8[2]);
				address2.setAddType("Residence");
				address2.setZipCode(splt8[3]);
				addresses.add(address2);
			}
			emp.setAddress(addresses);

			Department department = new Department();
			department.setName(record[9]);
			department.setDescription(record[10]);
			emp.setDepartment(department);

			employees.add(emp);
		}
		csvreader.close();

		return employees;
	}

}
