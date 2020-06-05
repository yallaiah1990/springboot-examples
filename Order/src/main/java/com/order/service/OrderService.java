package com.order.service;

import java.util.List;

import com.order.dto.OrderDto;

public interface OrderService {

	List<OrderDto> getAllOrder();

	OrderDto getOrderDetails(int id);

	String saveOrder(OrderDto orderRequest);

	OrderDto updateOrder(int id, OrderDto orderRequest);

	String deleteOrder(int id);

	String cancelOrder(int id);

}
