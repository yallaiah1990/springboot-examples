package com.employeemngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
public class Address {
	@Id
	private String id;
	private String street;
	private String zipCode;
	private String city;
	private String country;
	private String addType;

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getStreet() {
		return street;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}