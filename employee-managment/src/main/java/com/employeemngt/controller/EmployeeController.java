package com.employeemngt.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employeemngt.model.Employee;
import com.employeemngt.model.RequestObject;
import com.employeemngt.service.impl.EmployeeServiceImpl;
import com.employeemngt.util.AbstractMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employee")
@Api("Employee all Operation")
public class EmployeeController extends AbstractMapper {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeServiceImpl employeeservice;

	@PostMapping("/addEmployee")
	@ApiOperation(value = "adding new employee", notes = "adding new employee", response = EmployeeController.class)
	public ResponseEntity<Employee> create(@RequestBody RequestObject requestObject) {
		Employee employee = (Employee) getParsedObject(requestObject.getRequestdata(), Employee.class);
		logger.info("adding employee data");
		return new ResponseEntity<>(employeeservice.addEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/getemployeeById/{employeeId}")
	public ResponseEntity<Employee> employeeById(@PathVariable("employeeId") String employeeId) {
		return new ResponseEntity<>(employeeservice.getEmployeeById(employeeId), HttpStatus.OK);
	}

	@GetMapping("/getAllemployees")
	public ResponseEntity<List<Employee>> getAllemployees() {
		System.out.println("Thread : " + Thread.currentThread().getName());
		return new ResponseEntity<>(employeeservice.getAllEmployees(), HttpStatus.OK);
	}

	@PutMapping("/updateEmployeeByID/{employeeId}")
	public ResponseEntity<Employee> updateEmployeeByID(@RequestBody RequestObject requestObject,
			@PathVariable(value = "employeeId", required = false) String employeeId) {
		Employee employee = (Employee) getParsedObject(requestObject.getRequestdata(), Employee.class);
		logger.info(String.format("update employee by ID : %s", employeeId));
		employee.setId(employeeId);
		return new ResponseEntity<>(employeeservice.editEmployeeDetails(employee), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteEmployeeByID/{employeeId}")
	public void deleteEmployeeByID(@PathVariable(value = "employeeId", required = true) String employeeId) {
		employeeservice.deleteEmployeeByid(employeeId);
	}

	@PostMapping("/addListOfEmployee")
	public ResponseEntity<List<Employee>> addListOfEmployees(@RequestBody MultipartFile file)
			throws IOException, ParseException {
		return new ResponseEntity<>(employeeservice.addListOfEmployees(file), HttpStatus.CREATED);
	}
	
	
	
	@GetMapping
	public String testMongDbConnection() {
		
		
		return "Connected";
	}

}
