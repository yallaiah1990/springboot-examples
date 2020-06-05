package com.demo.customer360.Customer360.Model;

import java.util.Date;

import com.demo.customer360.Customer360.response.Response;

public abstract class Entity {
	private String id;
	private Date created;
	private Date updated;
	private long cas;
	
	private Response response;
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	protected Entity() {
		created = updated = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return getClass().getName();
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public long getCas() {
		return cas;
	}

	public void setCas(long cas) {
		this.cas = cas;
	}

}
