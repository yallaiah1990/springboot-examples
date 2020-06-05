package com.customer.dto;

public class CustomerDto {

	private String customerId;
	private String customerName;
	private String customerEmail;
	private Long customerMobile;
	
	public CustomerDto() {
		// default constructor
	}
	
	public CustomerDto(String customerId, String customerName, String customerEmail, Long customerMobile) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerMobile = customerMobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Long getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}
	
	
}
