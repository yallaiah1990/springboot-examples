package com.employeemngt.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.Employee;
import com.employeemngt.repository.AddressHistoryRepository;
import com.employeemngt.repository.AddressRepository;
import com.employeemngt.repository.DepartmentHistoryRepository;
import com.employeemngt.repository.DepartmentRepository;
import com.employeemngt.repository.EmployeeHistoryRepository;
import com.employeemngt.repository.EmployeeIdSequenceRepositoryImpl;
import com.employeemngt.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	public EmployeeServiceImpl employeeServiceimpl;

	@Mock
	EmployeeRepository employeerepository;

	@Mock
	AddressRepository addressrepository;

	@Mock
	DepartmentRepository departmentrepository;

	@Mock
	EmployeeHistoryRepository employeehistorypository;

	@Mock
	AddressHistoryRepository addresshistoryrepository;

	@Mock
	DepartmentHistoryRepository departmenthistoryrepository;

	@Mock
	EmployeeIdSequenceRepositoryImpl employeeidSeq;

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
		address2.setAddType("Prasent");

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
	public void addEmployeeTest() {
		when(addressrepository.save(anyListOf(Address.class))).thenReturn(employee.getAddress());
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());
		when(employeerepository.save(any(Employee.class))).thenReturn(employee);

		// when add the employee
		Employee employeeResult = employeeServiceimpl.addEmployee(employee);

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("9874563214", is(employeeResult.getPhoneNumber()));

		assertThat("banglore", is(employeeResult.getAddress().get(0).getCity()));
		assertThat("india", is(employeeResult.getAddress().get(0).getCountry()));
		assertThat("main road", is(employeeResult.getAddress().get(0).getStreet()));
		assertThat("560037", is(employeeResult.getAddress().get(0).getZipCode()));
		assertThat("Prasent", is(employeeResult.getAddress().get(0).getAddType()));

		assertThat("account", is(employeeResult.getDepartment().getName()));
		assertThat("account related", is(employeeResult.getDepartment().getDescription()));
	}

	@Test
	public void getEmployeeByIdTest() {

		when(employeerepository.findOne(anyString())).thenReturn(employee);

		// when
		Employee employeeResult = employeeServiceimpl.getEmployeeById(employee.getId());

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("9874563214", is(employeeResult.getPhoneNumber()));

		assertThat("banglore", is(employeeResult.getAddress().get(0).getCity()));
		assertThat("india", is(employeeResult.getAddress().get(0).getCountry()));
		assertThat("main road", is(employeeResult.getAddress().get(0).getStreet()));
		assertThat("560037", is(employeeResult.getAddress().get(0).getZipCode()));
		assertThat("Prasent", is(employeeResult.getAddress().get(0).getAddType()));

		assertThat("account", is(employeeResult.getDepartment().getName()));
		assertThat("account related", is(employeeResult.getDepartment().getDescription()));

		verify(employeerepository, times(1)).findOne(eq("12"));
	}

	@Test
	public void editEmployeeDetailsTest() {

		when(employeerepository.findOne(anyString())).thenReturn(employee);
		when(addressrepository.save(anyListOf(Address.class))).thenReturn(employee.getAddress());
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());
		when(employeerepository.save(any(Employee.class))).thenReturn(employee);

		// when
		Employee employeeResult = employeeServiceimpl.editEmployeeDetails(employee);

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("9874563214", is(employeeResult.getPhoneNumber()));

		assertThat("banglore", is(employeeResult.getAddress().get(0).getCity()));
		assertThat("india", is(employeeResult.getAddress().get(0).getCountry()));
		assertThat("main road", is(employeeResult.getAddress().get(0).getStreet()));
		assertThat("560037", is(employeeResult.getAddress().get(0).getZipCode()));
		assertThat("Prasent", is(employeeResult.getAddress().get(0).getAddType()));

		assertThat("account", is(employeeResult.getDepartment().getName()));
		assertThat("account related", is(employeeResult.getDepartment().getDescription()));

		verify(employeerepository, times(1)).findOne(eq(employee.getId()));

	}

	@Test
	public void getAllEmployeesTest() {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeerepository.findAll()).thenReturn(employees);

		// when
		List<Employee> employeeResult = employeeServiceimpl.getAllEmployees();

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.get(0).getFirstName()));
		assertThat("Vardan", is(employeeResult.get(0).getLastName()));
		assertThat("male", is(employeeResult.get(0).getGender()));
		assertThat("vishnu@gmail.com", is(employeeResult.get(0).getGmail()));
		assertThat("9874563214", is(employeeResult.get(0).getPhoneNumber()));

		assertThat("banglore", is(employeeResult.get(0).getAddress().get(0).getCity()));
		assertThat("india", is(employeeResult.get(0).getAddress().get(0).getCountry()));
		assertThat("main road", is(employeeResult.get(0).getAddress().get(0).getStreet()));
		assertThat("560037", is(employeeResult.get(0).getAddress().get(0).getZipCode()));
		assertThat("Prasent", is(employeeResult.get(0).getAddress().get(0).getAddType()));

		assertThat("account", is(employeeResult.get(0).getDepartment().getName()));
		assertThat("account related", is(employeeResult.get(0).getDepartment().getDescription()));
	}

	@Test
	public void addListOfEmployeesTest() throws IOException, ParseException {

		List<Employee> employeesdata = new ArrayList<>();
		employeesdata.add(employee);

		when(addressrepository.save(anyListOf(Address.class))).thenReturn(employee.getAddress());
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());
		when(employeerepository.save(any(Employee.class))).thenReturn(employee);

		List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		String content = "firstName,lastName,gender,gmail,phoneNumber,dateOfJoin,dateOfBirth,address__Present,address__Residence,department__name,department__description\n"
				+ "Vishnu,Vardan,male,vishnu@gmail.com,5/27/2019,5/2/1991,9874563214,\"mainRoad,banglore,india,560037\",\"mainRoad,banglore,india,560037\",account,account related";

		MockMultipartFile csvfile = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		// when
		List<Employee> employeeResult = employeeServiceimpl.addListOfEmployees(csvfile);

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.get(0).getFirstName()));
		assertThat("Vardan", is(employeeResult.get(0).getLastName()));
		assertThat("vishnu@gmail.com", is(employeeResult.get(0).getGmail()));
		assertThat("male", is(employeeResult.get(0).getGender()));
		assertThat("9874563214", is(employeeResult.get(0).getPhoneNumber()));

		assertThat("banglore", is(employeeResult.get(0).getAddress().get(0).getCity()));
		assertThat("india", is(employeeResult.get(0).getAddress().get(0).getCountry()));
		assertThat("main road", is(employeeResult.get(0).getAddress().get(0).getStreet()));
		assertThat("560037", is(employeeResult.get(0).getAddress().get(0).getZipCode()));
		assertThat("Prasent", is(employeeResult.get(0).getAddress().get(0).getAddType()));

		assertThat("account", is(employeeResult.get(0).getDepartment().getName()));
		assertThat("account related", is(employeeResult.get(0).getDepartment().getDescription()));
	}
	
	@Test
	public void addListOfEmployeesWithOutAddressTest() throws IOException, ParseException {

		List<Employee> employeesdata = new ArrayList<>();
		employeesdata.add(employee);

		when(addressrepository.save(anyListOf(Address.class))).thenReturn(employee.getAddress());
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());
		when(employeerepository.save(any(Employee.class))).thenReturn(employee);

		List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		String content = "firstName,lastName,gender,gmail,phoneNumber,dateOfJoin,dateOfBirth,address__Present,address__Residence,department__name,department__description\n"
				+ "Vishnu,Vardan,male,vishnu@gmail.com,5/27/2019,5/2/1991,9874563214,,,account,account related";

		MockMultipartFile csvfile = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		// when
		List<Employee> employeeResult = employeeServiceimpl.addListOfEmployees(csvfile);

		// then return saved employee data
		assertThat("Vishnu", is(employeeResult.get(0).getFirstName()));
		assertThat("Vardan", is(employeeResult.get(0).getLastName()));
		assertThat("vishnu@gmail.com", is(employeeResult.get(0).getGmail()));
		assertThat("male", is(employeeResult.get(0).getGender()));
		assertThat("9874563214", is(employeeResult.get(0).getPhoneNumber()));

		assertThat("account", is(employeeResult.get(0).getDepartment().getName()));
		assertThat("account related", is(employeeResult.get(0).getDepartment().getDescription()));
	}


	@Test
	public void deleteEmployeeByidTest() {

		when(employeerepository.findOne(anyString())).thenReturn(employee);

		employeeServiceimpl.deleteEmployeeByid("12");

		verify(employeerepository, times(1)).findOne(eq("12"));

	}
}
