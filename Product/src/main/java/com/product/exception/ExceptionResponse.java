package com.product.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String name;
	private String details;
	
	public ExceptionResponse(Date timeStamp, String name, String details) {
		super();
		this.timeStamp = timeStamp;
		this.name = name;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
