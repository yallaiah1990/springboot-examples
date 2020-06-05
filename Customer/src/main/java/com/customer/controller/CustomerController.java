package com.customer.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.dto.CustomerDto;
import com.customer.dto.ProductDto;
import com.customer.dto.ResponseObject;
import com.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RefreshScope
@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ObjectMapper objMapper;
	@Autowired
	CustomerService customerService;

	@Value("${productUrl:Config-Server is not working. Please check...}")
	private String productUrl;

	@RequestMapping(path = "/getExample", method = RequestMethod.GET)
	public String getString() {
		return "customer-service";
	}

	@RequestMapping(path = "/getAllCustomer", method = RequestMethod.GET)
	public ResponseObject getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@RequestMapping(path = "/getAllProducts", method = RequestMethod.GET)
	public ProductDto[] getAllProducts() {
		LOG.info("product-service url from config-server properties file :" + productUrl);
		String response = restTemplate.getForObject(productUrl + "getAllProducts", String.class);
		ProductDto[] list = null;
		try {
			list = objMapper.readValue(response, ProductDto[].class);
		} catch (Exception e) {
			LOG.info("Exception.." + e);
		}
		return list;
	}

	@RequestMapping(path = "/getCustomerById/{id}", method = RequestMethod.GET)
	public ResponseObject getCustomerDetails(@PathVariable String id) {
		return customerService.getCustomerDetails(id);
	}

	@RequestMapping(path = "/saveCustomer", method = RequestMethod.POST)
	public ResponseObject saveCustomer(@RequestBody CustomerDto dto) {
		return customerService.saveCustomer(dto);
	}

	@RequestMapping(path = "/updateCustomer/{id}", method = RequestMethod.PUT)
	public ResponseObject updateCustomer(@PathVariable String id, @RequestBody CustomerDto dto) {
		return customerService.updateCustomer(id, dto);
	}

	@RequestMapping(path = "/deleteCustomer/{id}", method = RequestMethod.DELETE)
	public ResponseObject deleteCustomer(@PathVariable String id) {
		return customerService.deleteCustomer(id);
	}

}
