package com.employeemngt.service;

import java.util.List;

import com.employeemngt.model.Department;

public interface DepartmentService {

	public Department addDepartment(Department department);

	public Department getDepartmentById(String departmentId);

	public Department editDepartmentDetails(Department department);

	public String deleteDepartmentByid(String departmentId);

	public List<Department> getAllDepartment();
}
