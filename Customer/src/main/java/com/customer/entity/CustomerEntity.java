package com.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_DETAILS")
public class CustomerEntity {
	@Id
	private Long custId;
	private String custName;
	private String custEmail;
	private Long custMobile;

	
	
	public CustomerEntity(Long custId, String custName, String custEmail, Long custMobile) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custMobile = custMobile;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public Long getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(Long custMobile) {
		this.custMobile = custMobile;
	}

	public CustomerEntity() {
		//default constructor 
	}
}
