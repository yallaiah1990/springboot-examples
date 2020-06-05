package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.DepartmentHistory;
import com.employeemngt.model.Employee;

@Repository
public interface DepartmentHistoryRepository extends MongoRepository<DepartmentHistory, String> {
}
