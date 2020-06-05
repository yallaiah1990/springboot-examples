package com.orderHistory.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderHistory.entity.OrderHistoryEntity;
import com.orderHistory.repo.OrderHistoryRepo;
import com.orderHistory.request.OrderHistoryRequest;
import com.orderHistory.response.OrderHistoryResponse;
import com.orderHistory.service.OrderHistoryService;
import org.modelmapper.ModelMapper;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{

	@Autowired
	OrderHistoryRepo orderHistoryRepo;

	@Override
	public List<OrderHistoryResponse> getAllOrderHistory() {
				
		/*
		List<OrderHistoryResponse> orderResponseList = new ArrayList<OrderHistoryResponse>();
		List<OrderHistoryEntity> orderList = orderHistoryRepo.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (OrderHistoryEntity orderEntity : orderList) {
			System.out.println(orderEntity.getOrderId()+" "+ orderEntity.getOrderQuantity());
			orderResponseList.add(modelMapper.map(orderEntity, OrderHistoryResponse.class));
		}
		
		return orderResponseList;*/
		return null;
	}

	@Override
	public List<OrderHistoryResponse> getOrderHistoryById(String orderHistoryId) {
		
		List<OrderHistoryResponse> orderResponseList = new ArrayList<OrderHistoryResponse>();
		
		ModelMapper modelMapper = new ModelMapper();
		List<Long> ids = Arrays.asList(Long.parseLong(orderHistoryId));
		System.out.println(ids);
		List<OrderHistoryEntity> orderList  = orderHistoryRepo.findByIdOrderId(Long.parseLong(orderHistoryId));
		System.out.println(orderList.size()+" ********");
		for (OrderHistoryEntity orderEntity : orderList) {

			orderResponseList.add(modelMapper.map(orderEntity, OrderHistoryResponse.class));
		}
		return orderResponseList;
	}

	@Override
	public OrderHistoryResponse saveOrderHistory(OrderHistoryRequest orderHistoryRequest) {
		return null;
		
	}

	@Override
	public OrderHistoryResponse updateOrderHistory(String id, OrderHistoryRequest orderHistoryRequest) {
		return null;
		
	}

	@Override
	public String deleteOrderHistory(String id) {
		return id;
		
	}

}
