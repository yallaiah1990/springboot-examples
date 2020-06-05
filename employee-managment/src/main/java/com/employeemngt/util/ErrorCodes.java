package com.employeemngt.util;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
	// General
    BADREQUEST(400, HttpStatus.BAD_REQUEST),
    NOT_FOUND(404, HttpStatus.NOT_FOUND),
    UNKNOWN(500, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_AUTHORIZED(403, HttpStatus.FORBIDDEN),
    CONFLICT(409, HttpStatus.CONFLICT),
    
    DATABASE_ERROR(1001, HttpStatus.INTERNAL_SERVER_ERROR),
    CERTIFICATE_ERROR(1002, HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_ERROR(1003, HttpStatus.INTERNAL_SERVER_ERROR),
    USER_CONTEXT_EMPTY(1003, HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1005, HttpStatus.BAD_REQUEST),
    ERROR_JSON_STRUCTURE(1006, HttpStatus.BAD_REQUEST);

    private final int code;

    private final HttpStatus httpStatus;

    private ErrorCodes(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static String getEnumNameForValue(int code1) {
        for (ErrorCodes e : ErrorCodes.values()) {
            if (code1 == e.code)
                return e.name();
        }
		return null;
	}
}
