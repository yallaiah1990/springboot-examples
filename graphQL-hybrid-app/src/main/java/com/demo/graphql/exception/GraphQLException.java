package com.demo.graphql.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLException extends RuntimeException implements GraphQLError {

	/**
	 * Variable for default serialVersionUID
	 * Variable for custom error cause
	 * Variable for custom error message
	 */
	private static final long serialVersionUID = 1L;
	String errorCause;
	String errorMessage;

	/**
	 * Method that will set the custom error cause and message
	 */
	public GraphQLException(String errorMessage, String cause) {
		this.errorCause = cause;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Method that will set the custom error cause and message in graphql extensions field
	 */
	@Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();

        customAttributes.put("errorCode", this.errorCause);
        customAttributes.put("errorMessage", this.errorMessage);

        return customAttributes;
    }
	
	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}

}
