package com.employeemngt.exception;

import com.employeemngt.util.ErrorCodes;

public class UserAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorCodes errorcode;

	public UserAuthException(String message, Throwable cause, ErrorCodes errorcode) {
		super(message, cause);
		this.errorcode = errorcode;
	}

	public ErrorCodes getErrorcode() {
		return errorcode;
	}
}