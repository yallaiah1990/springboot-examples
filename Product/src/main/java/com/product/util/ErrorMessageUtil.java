package com.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.constant.ProductConstant;
import com.product.dto.ErrorResponse;


public class ErrorMessageUtil {

	public static String getErrorResponse(String errorCode, String errorDescription) {
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(errorCode);
		errorResponse.setErrorDescription(errorDescription);
		
		ObjectMapper objMapper=new ObjectMapper();
		String errorMessage="";
		try {
			errorMessage=objMapper.writeValueAsString(errorResponse);
		} catch (JsonProcessingException e) {
			errorMessage="";
			e.printStackTrace();
		}
		return errorMessage;
	}
	
	public static String getDataNotFoundException() {
		String errResponse=getErrorResponse(ProductConstant.DATA_NOT_FOUND_CODE,
				ProductConstant.DATA_NOT_FOUND_DESCRIPTION);
		return errResponse;
	}
}
