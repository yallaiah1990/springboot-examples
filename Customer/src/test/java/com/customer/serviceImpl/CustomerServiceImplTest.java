
package com.customer.serviceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.customer.constant.CustomerConstant;
import com.customer.dto.CustomerDto;
import com.customer.dto.ResponseObject;
import com.customer.entity.CustomerEntity;
import com.customer.repo.CustomerRepo;
import com.customer.utils.CommonResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	@Mock
	private CustomerRepo customerRepo;

	private ResponseObject responseObject;
	private CustomerDto dto;
	private CommonResponse commonRes;
	private CustomerEntity entity;

	@Before
	public void initialSetUp() {
		responseObject = new ResponseObject();

		dto = this.setCustomerDto();
		commonRes = this.setCommonResponse();
		entity = dtoToDomain(this.setCustomerDto());
		responseObject.setResDto(dto);
		responseObject.setCommonResponse(commonRes);
	}

	@Test
	public void testGetAllCustomer() throws Exception {
		List<CustomerEntity> entityList = Arrays.asList(this.setCustomerEntity(), this.setCustomerEntity());

		when(customerRepo.findAll()).thenReturn(entityList);
		ResponseObject res = customerServiceImpl.getAllCustomer();
		assertThat(2, is(res.getResponseListDto().size()));
		assertThat("Success", is(res.getCommonResponse().getMessage()));
		assertThat(200, is(res.getCommonResponse().getStatusCode()));

		List<CustomerEntity> emptyEntityList = null;
		when(customerRepo.findAll()).thenReturn(emptyEntityList);
		ResponseObject nullRes = customerServiceImpl.getAllCustomer();
		assertThat("Data not found", is(nullRes.getCommonResponse().getMessage()));
		assertThat(404, is(nullRes.getCommonResponse().getStatusCode()));
	}

	@Test
	public void testGetCustomerDetails() throws Exception {
		Optional<CustomerEntity> entity = Optional.of(this.setCustomerEntity());
		when(customerRepo.findById(Long.parseLong("1"))).thenReturn(entity);
		ResponseObject res = customerServiceImpl.getCustomerDetails("1");
		assertThat("Yallaiah", is(res.getResDto().getCustomerName()));
		assertThat("eswar@gmail.com", is(res.getResDto().getCustomerEmail()));
		assertThat("Success", is(res.getCommonResponse().getMessage()));
		assertThat(200, is(res.getCommonResponse().getStatusCode()));

		Optional<CustomerEntity> emptyEntity =  Optional.empty();
		when(customerRepo.findById(Long.parseLong("1"))).thenReturn(emptyEntity);
		ResponseObject emptyRes = customerServiceImpl.getCustomerDetails("1");
		assertThat("Data not found", is(emptyRes.getCommonResponse().getMessage()));
		assertThat(404, is(emptyRes.getCommonResponse().getStatusCode()));
	}

	@Test
	public void testSaveCustomer() throws Exception {
		when(customerRepo.save(dtoToDomain(this.setCustomerDto()))).thenReturn(entity);
		ResponseObject res = customerServiceImpl.saveCustomer(dto);
		assertThat("Success", is(res.getCommonResponse().getMessage()));
		assertThat(200, is(res.getCommonResponse().getStatusCode()));

		when(customerRepo.save(null)).thenReturn(null);
		ResponseObject nullRes = customerServiceImpl.saveCustomer(null);
		assertThat("Customer name cannot be null", is(nullRes.getCommonResponse().getMessage()));
		assertThat(400, is(nullRes.getCommonResponse().getStatusCode()));
	}

	@Test
	public void testUpdateCustomer() {
		Optional<CustomerEntity> optionalEntity = Optional.of(this.setCustomerEntity());
		when(customerRepo.findById(Long.parseLong("1"))).thenReturn(optionalEntity);
		when(customerRepo.save(dtoToDomain(this.setCustomerDto()))).thenReturn(entity);
		ResponseObject res = customerServiceImpl.updateCustomer(dto.getCustomerId(), dto);
		assertThat(CustomerConstant.CUSTOMER_DETAILS_NOT_UPDATED, is(res.getCommonResponse().getMessage()));
		assertThat(404, is(res.getCommonResponse().getStatusCode()));

		Optional<CustomerEntity> emptyEntity = Optional.empty();
		when(customerRepo.findById(Long.parseLong("1"))).thenReturn(emptyEntity);
		ResponseObject nullRes = customerServiceImpl.updateCustomer(dto.getCustomerId(), dto);
		assertThat("Data not found", is(nullRes.getCommonResponse().getMessage()));
		assertThat(404, is(nullRes.getCommonResponse().getStatusCode()));
	}

	@Test
	public void testDeleteCustomer() {
		when(customerRepo.existsById(Long.parseLong("1"))).thenReturn(true);
		// when(customerRepo.deleteById(Long.parseLong("1"))).thenReturn(true);
		ResponseObject res = customerServiceImpl.deleteCustomer(dto.getCustomerId());
		assertThat(CustomerConstant.CUSTOMER_FAILED_TO_DELETE, is(res.getCommonResponse().getMessage()));
		assertThat(400, is(res.getCommonResponse().getStatusCode()));

		/*
		 * when(customerRepo.existsById(Long.parseLong("4"))).thenReturn(false);
		 * ResponseObject
		 * nullRes=customerServiceImpl.deleteCustomer(dto.getCustomerId());
		 * assertThat("Data not found", is(nullRes.getCommonResponse().getMessage()));
		 * assertThat(404, is(nullRes.getCommonResponse().getStatusCode()));
		 */
	}

	private CustomerEntity dtoToDomain(CustomerDto dto) {
		CustomerEntity entity = new CustomerEntity();
		entity.setCustId(Long.valueOf(dto.getCustomerId()));
		entity.setCustName(dto.getCustomerName());
		entity.setCustEmail(dto.getCustomerEmail());
		entity.setCustMobile(dto.getCustomerMobile());
		return entity;
	}

	private CustomerDto domainToDto(CustomerEntity entity) {
		CustomerDto dto = new CustomerDto();
		dto.setCustomerId(String.valueOf(entity.getCustId()));
		dto.setCustomerName(entity.getCustName());
		dto.setCustomerEmail(entity.getCustEmail());
		dto.setCustomerMobile(entity.getCustMobile());
		return dto;
	}

	private CustomerDto setCustomerDto() {
		CustomerDto resDto = new CustomerDto();
		resDto.setCustomerId("1");
		resDto.setCustomerEmail("eswar@gmail.com");
		resDto.setCustomerMobile(998679223L);
		resDto.setCustomerName("Eswar");
		return resDto;
	}

	private CustomerEntity setCustomerEntity() {
		CustomerEntity cusEntity = new CustomerEntity();
		cusEntity.setCustId(1L);
		cusEntity.setCustName("Yallaiah");
		cusEntity.setCustMobile(9986719223L);
		cusEntity.setCustEmail("eswar@gmail.com");
		return cusEntity;
	}

	private CommonResponse setCommonResponse() {
		CommonResponse commonRes = new CommonResponse();
		commonRes.setStatusCode(200);
		commonRes.setMessage("Success");
		return commonRes;
	}
}
