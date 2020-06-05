package com.customer.utils;

public class CommonResponse {

	
	private int statusCode;
	private String message;
	
	public CommonResponse() {
		//default constructor 
	}
	
	public CommonResponse(int i, String message) {
		super();
		this.statusCode = i;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
