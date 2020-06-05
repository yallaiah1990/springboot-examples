package com.employeemngt.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.employeemngt.model.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee getEmployeeById(String employeeId);

	public Employee editEmployeeDetails(Employee employee);

	public void deleteEmployeeByid(String employeeId);

	public List<Employee> getAllEmployees();

	public List<Employee> addListOfEmployees(MultipartFile file) throws IOException, ParseException;
}
