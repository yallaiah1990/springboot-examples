package com.product.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2073840229180095536L;

	public ProductNotFoundException(){
		super("Data not found");
	}
	public ProductNotFoundException(String exception) {
		super(exception);
	}
	
	/*
	 * public Response toResponse(Exception exception) { return
	 * Response.status(Status.OK.getStatusCode()).entity(exception.getMessage())
	 * .type(MediaType.APPLICATION_JSON).build(); }
	 */
}
