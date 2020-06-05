package com.employeemngt.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.DepartmentHistory;
import com.employeemngt.model.Employee;
import com.employeemngt.repository.DepartmentHistoryRepository;
import com.employeemngt.repository.DepartmentRepository;
import com.employeemngt.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceImplTest {

	@InjectMocks
	DepartmentServiceImpl departmentServiceImpl;

	@Mock
	EmployeeRepository employeerepository;

	@Mock
	DepartmentRepository departmentrepository;

	@Mock
	DepartmentHistoryRepository departmenthistoryrepository;

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
		department.setId("12345");
		department.setName("account");
		department.setDescription("account info");

		employee.setAddress(addresses);
		employee.setDepartment(department);
	}

	@Test
	public void addDepartmentTest() {
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());

		Department demartmentResult = departmentServiceImpl.addDepartment(department);

		assertThat("12345", is(demartmentResult.getId()));
		assertThat("account", is(demartmentResult.getName()));
		assertThat("account info", is(demartmentResult.getDescription()));
	}

	@Test
	public void getDepartmentByIdTest() {

		when(departmentrepository.findOne(anyString())).thenReturn(employee.getDepartment());

		Department demartmentResult = departmentServiceImpl.getDepartmentById("12345");

		assertThat("12345", is(demartmentResult.getId()));
		assertThat("account", is(demartmentResult.getName()));
		assertThat("account info", is(demartmentResult.getDescription()));
	}

	@Test
	public void editDepartmentDetailsTest() {
		when(departmentrepository.save(any(Department.class))).thenReturn(employee.getDepartment());

		Department demartmentResult = departmentServiceImpl.editDepartmentDetails(department);

		assertThat("12345", is(demartmentResult.getId()));
		assertThat("account", is(demartmentResult.getName()));
		assertThat("account info", is(demartmentResult.getDescription()));
	}

	@Test
	public void deleteDepartmentByidTest() {
		List<Employee> employees = new ArrayList<>();

		employees.add(employee);

		when(employeerepository.findAll()).thenReturn(employees);

		String result = departmentServiceImpl.deleteDepartmentByid("12345");

		assertThat("Requested Department is assigned to some employees please remove those employees and delete departments..", is(result));
	}
	
	@Test
	public void deleteDepartmentByidNotFoundTest() {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		DepartmentHistory departmentHistory = new DepartmentHistory();
		departmentHistory.setId("12345");
		departmentHistory.setName("account");
		departmentHistory.setDescription("account info");

		when(employeerepository.findAll()).thenReturn(employees);
		when(departmentrepository.findOne(anyString())).thenReturn(employee.getDepartment());
		when(departmenthistoryrepository.save(any(DepartmentHistory.class))).thenReturn(departmentHistory);
		doNothing().when(departmentrepository).delete((anyString()));

		String result = departmentServiceImpl.deleteDepartmentByid("123456");

		assertThat("Requested Department is deleted  : 123456", is(result));
	}

	@Test
	public void getAllDepartmentTest() {
		List<Department> departments = new ArrayList<>();

		departments.add(department);

		when(departmentrepository.findAll()).thenReturn(departments);

		List<Department> demartmentResult = departmentServiceImpl.getAllDepartment();

		assertThat("12345", is(demartmentResult.get(0).getId()));
		assertThat("account", is(demartmentResult.get(0).getName()));
		assertThat("account info", is(demartmentResult.get(0).getDescription()));
	}
}
