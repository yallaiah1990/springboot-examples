package com.legacydemo.model;

public class SigninPayload {

	private final User user;
	private final String token;
	
	public SigninPayload(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}
	
}
