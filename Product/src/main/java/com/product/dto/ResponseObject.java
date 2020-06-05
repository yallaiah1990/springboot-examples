package com.product.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.product.util.CommonResponse;

//@JsonInclude(value = Include.NON_EMPTY.NON_NULL)
public class ResponseObject {

	private CommonResponse commonResponse; 
	private ProductDto responseDto;
	private List<ProductDto> responseListDto;
	private String description;
	
	public ResponseObject(CommonResponse commonResponse, ProductDto resDto, String description) {
		super();
		this.commonResponse = commonResponse;
		this.responseDto = resDto;
		this.description = description;
	}
	public ResponseObject(CommonResponse commonResponse, List<ProductDto> resDto, String description) {
		super();
		this.commonResponse = commonResponse;
		this.responseListDto = resDto;
		this.description = description;
	}
	
	public List<ProductDto> getResponseListDto() {
		return responseListDto;
	}
	public void setResponseListDto(List<ProductDto> responseListDto) {
		this.responseListDto = responseListDto;
	}
	public CommonResponse getCommonResponse() {
		return commonResponse;
	}
	public void setCommonResponse(CommonResponse commonResponse) {
		this.commonResponse = commonResponse;
	}
	public ProductDto getResDto() {
		return responseDto;
	}
	public void setResDto(ProductDto resDto) {
		this.responseDto = resDto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
