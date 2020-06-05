package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
