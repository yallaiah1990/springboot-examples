package com.customer.dto;

import java.util.List;

import com.customer.utils.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class ResponseObject {

	private CommonResponse commonResponse; 
	private CustomerDto responseDto;
	private List<CustomerDto> responseListDto;
	
	public ResponseObject() {
		// default constructor
	}
	public ResponseObject(CommonResponse commonResponse, CustomerDto resDto) {
		super();
		this.commonResponse = commonResponse;
		this.responseDto = resDto;
	}
	public ResponseObject(CommonResponse commonResponse, List<CustomerDto> resDto) {
		super();
		this.commonResponse = commonResponse;
		this.responseListDto = resDto;
	}
	
	public List<CustomerDto> getResponseListDto() {
		return responseListDto;
	}
	public void setResponseListDto(List<CustomerDto> responseListDto) {
		this.responseListDto = responseListDto;
	}
	public CommonResponse getCommonResponse() {
		return commonResponse;
	}
	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}
	public CustomerDto getResDto() {
		return responseDto;
	}
	public void setResDto(CustomerDto resDto) {
		this.responseDto = resDto;
	}
	
}
