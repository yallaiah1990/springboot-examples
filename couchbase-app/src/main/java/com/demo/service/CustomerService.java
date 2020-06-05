package com.demo.service;

import java.util.List;

import com.demo.model.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomer();
	public Customer getCustomerById(String id);
	public Customer saveCustomer(Customer cust);
	public String deleteCustomer(String id);
	public Customer updateCustomer(String id, Customer cust);
	
}
