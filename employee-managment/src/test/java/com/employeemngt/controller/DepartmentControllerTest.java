package com.employeemngt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Department;
import com.employeemngt.model.RequestObject;
import com.employeemngt.service.impl.DepartmentServiceImpl;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class DepartmentControllerTest {

	@InjectMocks
	public DepartmentController departmentcontroller;

	private Department department;

	private RequestObject requestObject;

	@Mock
	public DepartmentServiceImpl departmentservice;

	@Before
	public void initiaSetup() throws ParseException {
		requestObject = new RequestObject();
		department = new Department();
		department.setName("account");
		requestObject.setRequestdata(department);
	}

	@Test
	public void createEmployeeTest() {
		when(departmentservice.addDepartment(anyObject())).thenReturn(department);
		ResponseEntity<Department> actualResult = departmentcontroller.create(requestObject);
		Department departmenteResult = actualResult.getBody();
		assertThat("account", is(departmenteResult.getName()));
	}

	@Test
	public void departmentByIdTest() {
		when(departmentservice.getDepartmentById(anyObject())).thenReturn(department);
		ResponseEntity<Department> actualResult = departmentcontroller.departmentById(department.getId());
		Department departmenteResult = actualResult.getBody();
		assertThat("account", is(departmenteResult.getName()));
	}

	@Test
	public void getAllDepartmentsTest() {
		List<Department> departments = new ArrayList<>();
		departments.add(department);
		when(departmentservice.getAllDepartment()).thenReturn(departments);
		ResponseEntity<List<Department>> actualResult = departmentcontroller.getAllDepartments();
		List<Department> employeeResult = actualResult.getBody();
		assertThat("account", is(employeeResult.get(0).getName()));
	}

	@Test
	public void updateDepartmentByIDTest() {
		when(departmentservice.editDepartmentDetails(anyObject())).thenReturn(department);
		ResponseEntity<Department> actualResult = departmentcontroller.updateDepartmentByID(requestObject,
				department.getId());
		Department departmentResult = actualResult.getBody();
		assertThat("account", is(departmentResult.getName()));
	}

	@Test
	public void deleteDepartmentByIDTest() {
		when(departmentservice.deleteDepartmentByid(anyObject())).thenReturn(
				"Requested Department is assigned to some employees please remove those employees and delete departments..");

		String result = departmentcontroller.deleteDepartmentByID(department.getId());

		assertThat(
				"Requested Department is assigned to some employees please remove those employees and delete departments..",
				is(result));
	}

}
