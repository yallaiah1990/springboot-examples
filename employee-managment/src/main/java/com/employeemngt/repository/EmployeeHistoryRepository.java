package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.EmployeeHistory;

@Repository
public interface EmployeeHistoryRepository extends MongoRepository<EmployeeHistory, String> {

}
