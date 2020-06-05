package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.demo.config.Config;
import com.demo.model.Customer;
import com.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;
	Bucket bucket = null;
	
	
	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>) custRepo.findAll();
	}

	@Override
	public Customer getCustomerById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer saveCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomer(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(String id, Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

}
