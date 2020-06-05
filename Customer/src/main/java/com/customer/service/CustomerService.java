package com.customer.service;

import com.customer.dto.CustomerDto;
import com.customer.dto.ResponseObject;

public interface CustomerService {

	ResponseObject getAllCustomer();

	ResponseObject getCustomerDetails(String id);

	ResponseObject saveCustomer(CustomerDto dto);

	ResponseObject updateCustomer(String id, CustomerDto customerRequest);

	ResponseObject deleteCustomer(String id);

}
