package com.order.controller;

import java.util.List;

import com.netflix.discovery.DiscoveryClient;
import com.order.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	@RequestMapping(path = "/getExample", method = RequestMethod.GET)
	public String getString() {
		return "order-service";
	}
	
	
	@RequestMapping(path = "/getAllOrder", method = RequestMethod.GET)
	public List<OrderDto> getAllOrder() {
		return orderService.getAllOrder();
	}

	@RequestMapping(path = "/getOrderDetails/{id}", method = RequestMethod.GET)
	public OrderDto getOrderDetails(@PathVariable int id) {
		return orderService.getOrderDetails(id);
	}

	@RequestMapping(path = "/createOrder", method = RequestMethod.POST)
	public String saveOrder(@RequestBody OrderDto orderRequest) {
		log.info("inside createorder");
		return orderService.saveOrder(orderRequest);

	}

	@RequestMapping(path = "/updateOrder/{id}", method = RequestMethod.PUT)
	public OrderDto updateOrder(@PathVariable int id, @RequestBody OrderDto orderRequest) {
		return orderService.updateOrder(id, orderRequest);

	}

	@PutMapping(path="/cancelOrder/{orderId}")
	public String cancelOrder(@PathVariable int orderId) {
		return orderService.cancelOrder(orderId);
	}

	@RequestMapping(path = "/deleteOrder/{id}", method = RequestMethod.DELETE)
	public String deleteOrder(@PathVariable int id) {
		return orderService.deleteOrder(id);
	}

}
