package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.AddressHistory;

@Repository
public interface AddressHistoryRepository extends MongoRepository<AddressHistory, String> {
	
}
