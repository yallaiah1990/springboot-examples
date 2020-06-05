package com.employeemngt.model;

import java.util.Date;

public class EmployeeCSVReportData {

	private String id;
	private String firstName;
	private String lastName;
	private String gmail;
	private String gender;
	private Date dateOfJoin;
	private Date dateOfBirth;
	private String phoneNumber;
	private String addressPresent;
	private String addressResidence;
	private String departmentName;
	private String departmentDescription;

	public String getAddressPresent() {
		return addressPresent;
	}

	public void setAddressPresent(String addressPresent) {
		this.addressPresent = addressPresent;
	}

	public String getAddressResidence() {
		return addressResidence;
	}

	public void setAddressResidence(String addressResidence) {
		this.addressResidence = addressResidence;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
