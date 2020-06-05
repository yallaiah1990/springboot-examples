package com.accenture.customer360.Customer360.Model;

public class Customer extends Entity {

	customer360 customer360;

	public customer360 getCustomer360() {
		return customer360;
	}

	public void setCustomer360(customer360 customer360) {
		this.customer360 = customer360;
	}

	@Override
	public String toString() {
		return "Customer [customer360=" + customer360 + "]";
	}
}