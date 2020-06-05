package com.orderHistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orderHistory.request.OrderHistoryRequest;
import com.orderHistory.response.OrderHistoryResponse;
import com.orderHistory.service.OrderHistoryService;

@RestController
@RequestMapping("/history")
public class OrderHistoryController {
	@Autowired
	OrderHistoryService orderHistoryService;
	
	@RequestMapping(path = "/getExample", method = RequestMethod.GET)
	public String getString() {
		return "history-service";
	}

	@RequestMapping(path = "/getAllOrderHistory", method = RequestMethod.GET)
	public List<OrderHistoryResponse> getAllOrderHistory() {
		return orderHistoryService.getAllOrderHistory();
	}

	@RequestMapping(path = "/getOrderHistoryById/{id}", method = RequestMethod.GET)
	public List<OrderHistoryResponse> getOrderHistoryById(@PathVariable String id) {
		return orderHistoryService.getOrderHistoryById(id);
	}

	@RequestMapping(path = "/saveOrderHistory", method = RequestMethod.POST)
	public OrderHistoryResponse saveOrderHistory(@RequestBody OrderHistoryRequest orderHistoryRequest) {
		return orderHistoryService.saveOrderHistory(orderHistoryRequest);

	}

	@RequestMapping(path = "/updateOrderHistory/{id}", method = RequestMethod.PUT)
	public OrderHistoryResponse updateOrderHistory(@PathVariable String id,
			@RequestBody OrderHistoryRequest orderHistoryRequest) {
		return orderHistoryService.updateOrderHistory(id, orderHistoryRequest);

	}

	@RequestMapping(path = "/deleteOrderHistory/{id}", method = RequestMethod.DELETE)
	public String deleteOrderHistory(@PathVariable String id) {
		return orderHistoryService.deleteOrderHistory(id);
	}

}
