package com.accenture.customer360.Customer360.Model;

public class customer360 extends Entity {

	private String email;
	private String userName;

	@Override
	public String toString() {
		return "customer360 [email=" + email + ", userName=" + userName + ", lastName=" + lastName + ", firstName="
				+ firstName + "]";
	}

	public customer360() {
		super();
	}

	public customer360(String email, String userName, String lastName, String firstName) {
		super();
		this.email = email;
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String lastName;
	private String firstName;
}
