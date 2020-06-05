package com.customer.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.constant.CustomerConstant;
import com.customer.dto.CustomerDto;
import com.customer.dto.ResponseObject;
import com.customer.entity.CustomerEntity;
import com.customer.repo.CustomerRepo;
import com.customer.service.CustomerService;
import com.customer.utils.CommonResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public ResponseObject getAllCustomer() {
		List<CustomerDto> dtoList = new ArrayList<CustomerDto>();
		List<CustomerEntity> result = customerRepo.findAll();
		if (result != null && !result.isEmpty()) {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.OK.value(),
					CustomerConstant.SUCCESS_RESPONSE);
			for (CustomerEntity entity : result) {
				CustomerDto dto = domainToDto(entity);
				dtoList.add(dto);
			}
			return new ResponseObject(commonResponse, dtoList);
		} else {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.NOT_FOUND.value(),
					CustomerConstant.DATA_NOT_FOUND);
			return new ResponseObject(commonResponse, dtoList);
		}
	}

	@Override
	public ResponseObject getCustomerDetails(String id) {
		Optional<CustomerEntity> customerEntity = customerRepo.findById(Long.parseLong(id));
		if (customerEntity.isPresent()) {
			CustomerDto dto = optonalDomainToDto(customerEntity);
			CommonResponse commonResponse = new CommonResponse(HttpStatus.OK.value(),
					CustomerConstant.SUCCESS_RESPONSE);
			return new ResponseObject(commonResponse, dto);
		} else {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.NOT_FOUND.value(),
					CustomerConstant.DATA_NOT_FOUND);
			return new ResponseObject(commonResponse, new CustomerDto());
		}
	}

	@Override
	public ResponseObject saveCustomer(CustomerDto dto) {
		if (dto != null) {
			customerRepo.save(dtoToDomain(dto));
			CommonResponse commonResponse = new CommonResponse(HttpStatus.OK.value(),
					CustomerConstant.SUCCESS_RESPONSE);
			return new ResponseObject(commonResponse, dto);
		} else {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.BAD_REQUEST.value(),
					CustomerConstant.CUSTOMER_NAME_NULL);
			return new ResponseObject(commonResponse, new CustomerDto());
		}

	}

	@Override
	public ResponseObject updateCustomer(String id, CustomerDto dto) {
		Optional<CustomerEntity> result = customerRepo.findById(Long.parseLong(id));
		CustomerEntity entity = dtoToDomain(dto);
		if (result.isPresent()) {
			entity.setCustId(result.get().getCustId());
			CustomerEntity updatedResult = customerRepo.save(entity);
			if (updatedResult != null) {
				CustomerDto resDto = domainToDto(updatedResult);
				CommonResponse commonResponse = new CommonResponse(HttpStatus.OK.value(),
						CustomerConstant.SUCCESS_RESPONSE);
				return new ResponseObject(commonResponse, resDto);
			} else {
				CommonResponse commonResponse = new CommonResponse(HttpStatus.NOT_FOUND.value(),
						CustomerConstant.CUSTOMER_DETAILS_NOT_UPDATED);
				return new ResponseObject(commonResponse, new CustomerDto());
			}
		} else {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.NOT_FOUND.value(),
					CustomerConstant.DATA_NOT_FOUND);
			return new ResponseObject(commonResponse, new CustomerDto());
		}
	}

	@Override
	public ResponseObject deleteCustomer(String id) {
		if (customerRepo.existsById(Long.parseLong(id))) {
			customerRepo.deleteById(Long.parseLong(id));
			if (customerRepo.existsById(Long.parseLong(id))) {
				CommonResponse commonResponse = new CommonResponse(HttpStatus.BAD_REQUEST.value(),
						CustomerConstant.CUSTOMER_FAILED_TO_DELETE);
				return new ResponseObject(commonResponse, new CustomerDto());
			} else {
				CommonResponse commonResponse = new CommonResponse(HttpStatus.OK.value(),
						CustomerConstant.CUSTOMER_DELETED_SUCCESSFULLY);
				return new ResponseObject(commonResponse, new CustomerDto());
			}
		} else {
			CommonResponse commonResponse = new CommonResponse(HttpStatus.NOT_FOUND.value(),
					CustomerConstant.DATA_NOT_FOUND);
			return new ResponseObject(commonResponse, new CustomerDto());
		}
	}

	private CustomerDto domainToDto(CustomerEntity entity) {
		CustomerDto dto = new CustomerDto();
		dto.setCustomerId(String.valueOf(entity.getCustId()));
		dto.setCustomerName(entity.getCustName());
		dto.setCustomerEmail(entity.getCustEmail());
		dto.setCustomerMobile(entity.getCustMobile());
		return dto;
	}

	private CustomerDto optonalDomainToDto(Optional<CustomerEntity> entity) {
		CustomerDto dto = new CustomerDto();
		if (entity.isPresent()) {
			dto.setCustomerId(String.valueOf(entity.get().getCustId()));
			dto.setCustomerName(entity.get().getCustName());
			dto.setCustomerEmail(entity.get().getCustEmail());
			dto.setCustomerMobile(entity.get().getCustMobile());
		}
		return dto;
	}

	private CustomerEntity dtoToDomain(CustomerDto dto) {
		CustomerEntity entity = new CustomerEntity();
		entity.setCustId(Long.valueOf(dto.getCustomerId()));
		entity.setCustName(dto.getCustomerName());
		entity.setCustEmail(dto.getCustomerEmail());
		entity.setCustMobile(dto.getCustomerMobile());
		return entity;
	}
}
