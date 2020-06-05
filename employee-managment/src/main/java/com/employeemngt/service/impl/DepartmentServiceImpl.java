package com.employeemngt.service.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemngt.model.Department;
import com.employeemngt.model.DepartmentHistory;
import com.employeemngt.model.Employee;
import com.employeemngt.repository.DepartmentHistoryRepository;
import com.employeemngt.repository.DepartmentRepository;
import com.employeemngt.repository.EmployeeRepository;
import com.employeemngt.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	DepartmentRepository departmentrepository;

	@Autowired
	DepartmentHistoryRepository departmenthistoryrepository;

	@Autowired
	EmployeeRepository employeerepository;

	@Override
	public Department addDepartment(Department department) {
		return departmentrepository.save(department);
	}

	@Override
	public Department getDepartmentById(String departmentId) {
		return departmentrepository.findOne(departmentId);
	}

	@Override
	public Department editDepartmentDetails(Department department) {
		return departmentrepository.save(department);
	}

	@Override
	public String deleteDepartmentByid(String department_id) {
		logger.info("Checking Requested Depart is Assigned to any employee");
		Optional<Employee> employees = employeerepository.findAll().stream()
				.filter((employee) -> employee.getDepartment().getId().equals(department_id)).findAny();
		if (employees.isPresent()) {
			return "Requested Department is assigned to some employees please remove those employees and delete departments..";
		} else {
			DepartmentHistory departmenthistory = new DozerBeanMapper().map(getDepartmentById(department_id),
					DepartmentHistory.class);
			departmenthistoryrepository.save(departmenthistory);
			departmentrepository.delete(department_id);
			return "Requested Department is deleted  : " + department_id;
		}
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentrepository.findAll();
	}

}
