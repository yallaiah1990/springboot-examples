package com.orderHistory.service;

import java.util.List;

import com.orderHistory.request.OrderHistoryRequest;
import com.orderHistory.response.OrderHistoryResponse;


public interface OrderHistoryService {
	List<OrderHistoryResponse> getAllOrderHistory();

	List<OrderHistoryResponse> getOrderHistoryById(String orderHistoryId);

	OrderHistoryResponse saveOrderHistory(OrderHistoryRequest orderHistoryRequest);
	
	OrderHistoryResponse updateOrderHistory(String id,OrderHistoryRequest orderHistoryRequest);

	String deleteOrderHistory(String id);

}
