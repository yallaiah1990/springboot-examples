package com.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Customer;
import com.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	CustomerService customerService;

	@Value("${productUrl:Config-Server is not working. Please check...}")
	private String productUrl;

	@RequestMapping(path = "/getExample", method = RequestMethod.GET)
	public String getString() {
		return "customer-service";
	}

	@RequestMapping(path = "/getAllCustomer", method = RequestMethod.GET)
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@RequestMapping(path = "/getCustomerById/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable String id) {
		return customerService.getCustomerById(id);
	}

	@RequestMapping(path = "/saveCustomer", method = RequestMethod.POST)
	public Customer saveCustomer(@RequestBody Customer dto) {
		return customerService.saveCustomer(dto);
	}

	@RequestMapping(path = "/updateCustomer/{id}", method = RequestMethod.PUT)
	public Customer updateCustomer(@PathVariable String id, @RequestBody Customer dto) {
		return customerService.updateCustomer(id, dto);
	}

	@RequestMapping(path = "/deleteCustomer/{id}", method = RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable String id) {
		return customerService.deleteCustomer(id);
	}

}
