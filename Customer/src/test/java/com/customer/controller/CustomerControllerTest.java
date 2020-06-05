package com.customer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.customer.dto.CustomerDto;
import com.customer.dto.ProductDto;
import com.customer.dto.ResponseObject;
import com.customer.serviceImpl.CustomerServiceImpl;
import com.customer.utils.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	@Mock
	private CustomerServiceImpl customerService;
	@Mock
	private RestTemplate restTemlate;
	@Mock
	private ObjectMapper objMapper;
	
	private ResponseObject responseObject;	
	private CustomerDto dto;
	private ProductDto productDto;
	private CommonResponse commonRes;
	private String deleteMsg;
	private String productUrl=null;
	
	@Before
	public void initialSetUp() {
		responseObject=new ResponseObject();
		
		dto=this.setCustomerDto();
		productDto=this.setProductDto();
		commonRes=this.setCommonResponse();
		
		responseObject.setResDto(dto);
		responseObject.setCommonResponse(commonRes);
		
		deleteMsg="Requested Customer Id Deleted Succssfully :";
		
	}
	
	@Test
	public void testGetExample() {
		String actualResult=customerController.getString();
		assertThat("customer-service",is(actualResult));
	}
	
	@Test
	public void testGetAllCustomers() {
		CustomerDto custDto=new CustomerDto("1","Eswar","eswar@gmail.com",998719223L);
		//responseObject.setResDto(custDto);
		when(customerService.getAllCustomer()).thenReturn(responseObject);
		ResponseObject response=customerController.getAllCustomer();
		assertThat(dto, is(response.getResDto()));
		assertThat(commonRes, is(response.getCommonResponse()));
		assertThat(200, is(response.getCommonResponse().getStatusCode()));
		assertThat("Success", is(response.getCommonResponse().getMessage()));
		assertThat("eswar@gmail.com", is(response.getResDto().getCustomerEmail()));
		assertThat(9986719223L, is(response.getResDto().getCustomerMobile()));
	}
	
	@Test
	public void testGetAllProducts() throws Exception{
		productUrl="http://product-service/product/getAllProducts";
		when(restTemlate.getForObject(productUrl, String.class)).thenReturn(productDto.toString());
		when(objMapper.readValue(productDto.toString(), ProductDto[].class)).thenReturn(Arrays.array(productDto));
		ProductDto[] list=customerController.getAllProducts();
		assertThat("1", is(productDto.getProductId()));
		assertThat("samsung", is(productDto.getProductName()));
		assertThat(1000d, is(productDto.getProductPrice()));
		assertThat(4, is(productDto.getProductQty()));
	}
	
	@Test
	public void testGetCustomerDetails() {
		when(customerService.getCustomerDetails(dto.getCustomerId())).thenReturn(responseObject);
		ResponseObject response=customerController.getCustomerDetails(dto.getCustomerId());
		assertThat(dto, is(response.getResDto()));
		assertThat(commonRes, is(response.getCommonResponse()));
	}
	
	@Test
	public void testSaveCustomer() {
		when(customerService.saveCustomer(dto)).thenReturn(responseObject);
		ResponseObject response=customerController.saveCustomer(dto);
		assertThat(dto, is(response.getResDto()));
		assertThat(commonRes, is(response.getCommonResponse()));
	}
	
	
	@Test
	public void testUpdateCustomer() {
		when(customerService.updateCustomer(dto.getCustomerId(), dto)).thenReturn(responseObject);
		ResponseObject response=customerController.updateCustomer(dto.getCustomerId(), dto);
		assertThat(dto, is(response.getResDto()));
		assertThat(commonRes, is(response.getCommonResponse()));
	}
	
	@Test
	public void testDeleteCustomer() {
		when(customerService.deleteCustomer(dto.getCustomerId())).thenReturn(responseObject);
		ResponseObject res=customerController.deleteCustomer(dto.getCustomerId());
		assertThat("Success", is(res.getCommonResponse().getMessage()));
		assertThat(200, is(res.getCommonResponse().getStatusCode()));
	}
	
	
	
	private CustomerDto setCustomerDto() {
		CustomerDto resDto= new CustomerDto();
		resDto.setCustomerId("1");
		resDto.setCustomerEmail("eswar@gmail.com");
		resDto.setCustomerMobile(9986719223L);
		resDto.setCustomerName("Eswar");
		return resDto;
	}
	
	private ProductDto setProductDto() {
		ProductDto resDto= new ProductDto();
		resDto.setProductId("1");
		resDto.setProductName("samsung");
		resDto.setProductPrice(1000d);
		resDto.setProductQty(4);
		return resDto;
	}
	
	private CommonResponse setCommonResponse() {
		CommonResponse commonRes=new CommonResponse();
		commonRes.setStatusCode(200);
		commonRes.setMessage("Success");
		return commonRes;
	}
}

