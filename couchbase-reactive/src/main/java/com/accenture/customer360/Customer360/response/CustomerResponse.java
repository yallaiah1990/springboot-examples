package com.accenture.customer360.Customer360.response;

import com.accenture.customer360.Customer360.Model.customer360;

public class CustomerResponse extends Response {

	private customer360 customer360;

	public customer360 getCustomer360() {
		return customer360;
	}

	public void setCustomer360(customer360 customer360) {
		this.customer360 = customer360;
	}

	@Override
	public String toString() {
		return "CustomerResponse [customer360=" + customer360 + "]";
	}

}
