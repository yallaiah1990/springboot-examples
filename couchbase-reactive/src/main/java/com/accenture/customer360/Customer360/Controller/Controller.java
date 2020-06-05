package com.accenture.customer360.Customer360.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.customer360.Customer360.Model.Customer;
import com.accenture.customer360.Customer360.Model.customer360;
import com.accenture.customer360.Customer360.Service.service;
import com.accenture.customer360.Customer360.response.CustomerResponse;
import com.accenture.customer360.Customer360.response.Response;

@RestController
public class Controller {

	@Autowired
	service serviceClass;

	@GetMapping("/getAsyncData/{docId}")
	public customer360 getAsyncData(@PathVariable("docId") String docId) {
		return serviceClass.getDataAsyncly(docId);
	}
	
	@GetMapping("/getAsyncDataWithObservable/{docId}")
	public Customer getAsyncDataWithObservable(@PathVariable("docId") String docId) {
		return serviceClass.getDataWithObservable(docId);
	}
	

	@GetMapping("/n1ql/{userName}")
	public Customer selectWithN1QL(@PathVariable("userName") String userName) {
		Customer customer = serviceClass.selectWithN1QL(userName);
		return customer;
	}

	@PutMapping("/updateWithN1QL/{userId}")
	public ResponseEntity<CustomerResponse> updateWithN1QL(@RequestBody customer360 customer,
			@PathVariable("userId") String userId) {
		CustomerResponse customerObj = serviceClass.updateWithN1QL(customer, userId);
		return new ResponseEntity<CustomerResponse>(customerObj, HttpStatus.OK);
	}

	@GetMapping("/getCustomer/{customerId}")
	public customer360 getCustomerData(@PathVariable("customerId") String customerId) {
		customer360 customer = serviceClass.getCustomerData(customerId);
		return customer;
	}

	@PostMapping("/addNewCustomer/{customerId}")
	public customer360 addNewCustomer(@PathVariable("customerId") String id, @RequestBody customer360 customer) {
		customer360 newCustomer = null;
		try {
			newCustomer = serviceClass.addNewCustomer(customer, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCustomer;
	}

	@PutMapping("/updateCustomerDetails/{customerId}")
	public customer360 updateCustomerDetails(@PathVariable("customerId") String id, @RequestBody customer360 customer) {
		customer360 newCustomer = null;
		try {
			newCustomer = serviceClass.updateCustomerDetails(customer, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCustomer;

	}

	@PostMapping("/upsertCustomerDetails/{customerId}")
	public customer360 upsertCustomerDetails(@PathVariable("customerId") String id, @RequestBody customer360 customer) {
		customer360 newCustomer = new customer360();
		try {
			newCustomer = serviceClass.upsertCustomerDetails(customer, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCustomer;
	}

	@DeleteMapping("/deleteCustomerDetails/{docId}")
	public Response deleteCustomerDetails(@PathVariable("docId") String id) {
		Response response = new Response();
		try {
			response = serviceClass.deleteCustomerRecord(id);
			response.setResCode(HttpStatus.OK.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/createAsyncBatchDoc")
	public ResponseEntity<Response> createAsyncDoc() {

		Response res = new Response();
		res = serviceClass.createAsyncDoc();
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}

}
