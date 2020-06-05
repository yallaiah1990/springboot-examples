package com.accenture.customer360.Customer360.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.customer360.Customer360.Model.Customer;
import com.accenture.customer360.Customer360.Model.customer360;
import com.accenture.customer360.Customer360.Repository.CustomerRepository;
import com.accenture.customer360.Customer360.response.CustomerResponse;
import com.accenture.customer360.Customer360.response.Response;

@Service
public class service {

	@Autowired
	CustomerRepository repository;

	public customer360 getCustomerData(String customerId) {
		return repository.getCustomerData(customerId);
	}

	public customer360 addNewCustomer(customer360 customer, String id) throws Exception {
		return repository.addNewCustomer(customer, id);
	}

	public customer360 updateCustomerDetails(customer360 customer, String id) {
		return repository.updateCustomerDetails(customer, id);
	}

	public customer360 upsertCustomerDetails(customer360 customer, String id) {

		return repository.upsertCustomerRecord(customer, id);
	}

	public Response deleteCustomerRecord(String id) {

		return repository.deleteCustomerRecord(id);
	}

	public Customer selectWithN1QL(String userName) {
		return repository.selectWithN1QL(userName);
	}

	public CustomerResponse updateWithN1QL(customer360 customer, String userId) {
		return repository.updateWithN1Ql(customer, userId);

	}

	public customer360 getDataAsyncly(String docId) {

		return repository.getAsyncly(docId);
	}

	public Response createAsyncDoc() {

		return repository.createAsyncDocument();
	}

	public Customer getDataWithObservable(String docId) {
		return repository.getDataWithObservable(docId);
	}

}
