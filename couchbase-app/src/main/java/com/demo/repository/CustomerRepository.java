package com.demo.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Customer;

@Repository
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {

}
