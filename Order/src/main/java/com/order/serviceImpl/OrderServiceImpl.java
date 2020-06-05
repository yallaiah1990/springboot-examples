package com.order.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.order.controller.OrderController;
import com.order.dto.InventoryDto;
import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.util.Utility;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.order.constant.OrderConstant;
import com.order.entity.OrderEntity;
import com.order.repo.OrderRepo;
import com.order.service.OrderService;


@RefreshScope
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	private KafkaTemplate<String, OrderEntity> kafkaTemplate;

	@Value("${inventoryUrl:Config-Server is not working. Please check...}")
	private String inventoryUrl;
	@Value("${productUrl:Config-Server is not working. Please check...}")
	private String productUrl;

/*	@Autowired
	private DiscoveryClient discoveryClient;*/
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Override
	public List<OrderDto> getAllOrder() {

		List<OrderDto> orderResponseList = new ArrayList<OrderDto>();
		List<OrderEntity> orderList = orderRepo.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (OrderEntity orderEntity : orderList) {
			orderResponseList.add(modelMapper.map(orderEntity, OrderDto.class));
		}
		return orderResponseList;
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackSaveProductInventory", commandKey = "createOrderServiceCommand")
	public OrderDto getOrderDetails(int id) {
		OrderEntity orderResp =orderRepo.getOne(id);
		return Utility.convertEntityToResponse(orderResp);
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackSaveProductInventory", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50000") })
	@Transactional
	public String saveOrder(OrderDto dto) {

		if (dto.getOrderQuantity() > 0) {
			String ProductServiceId = "product-service";
			String InventoryServiceId = "inventory-service";

			ServiceInstance serviceInstance = this.loadBalancer.choose(ProductServiceId);
			String chosenUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/getProductById";
			log.info("Load Balancer choose:" + serviceInstance.getUri());

			//String urlProduct = productUrl + "getProductById/" + dto.getProductId();
			ProductDto productResponse = restTemplate.getForObject(chosenUrl, ProductDto.class);

			if (productResponse != null && productResponse.getProductId() != null) {
				String urlInv = inventoryUrl + "getInventoryById/" + dto.getProductId();
				InventoryDto inventoryResponse = restTemplate.getForObject(urlInv, InventoryDto.class);


				if (dto.getOrderQuantity() <= inventoryResponse.getInventoryQuantity()) {
					ModelMapper modelMapper = new ModelMapper();
					OrderEntity orderEntity = modelMapper.map(dto, OrderEntity.class);
					orderEntity.setOrderedDate(new Date());
					orderEntity.setOrderUpdatedDate(new Date());
					orderEntity.setOrderedProduct(productResponse.getProductName());
					orderEntity.setOrderStatus(OrderConstant.sucessMsg);
					OrderEntity result = orderRepo.save(orderEntity);
					// posting message of order created on kafka, which is listened by inventory and reduces the product quantity
					fireOrderCreatedEvent(orderEntity);
					return OrderConstant.sucessMsg + result.getOrderId();

				} else {
					return OrderConstant.unavailableQuan;
				}

			} else {
				return OrderConstant.unavailableProduct;
			}
		} else {
			return OrderConstant.selectQuantity;
		}

	}

	private void fireOrderCreatedEvent(OrderEntity order) {
		kafkaTemplate.send("order", "created", order);
	}

	private String fallbackSaveProductInventory(OrderDto dto) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderedDate(new Date());
		orderEntity.setOrderUpdatedDate(new Date());
		orderEntity.setOrderedProduct(dto.getOrderedProduct());
		orderEntity.setProductId(dto.getProductId());
		orderEntity.setOrderStatus(OrderConstant.orderPending);
		OrderEntity result = orderRepo.save(orderEntity);
		return OrderConstant.orderPending;
	}

	@Override
	public OrderDto updateOrder(int id, OrderDto orderRequest) {
		OrderEntity updateOrder = orderRepo.findById(id).get();
		updateOrder.setOrderStatus("Updated");
		updateOrder.setOrderUpdatedDate(new Date());
		updateOrder.setOrderQuantity(updateOrder.getOrderQuantity());
		orderRepo.save(updateOrder);
		//kafka impl is pending
		return Utility.convertEntityToResponse(updateOrder);
	}

	@Override
	public String deleteOrder(int id) {
		OrderEntity deleteOrder = orderRepo.findById(id).get();
		orderRepo.delete(deleteOrder);
		return "deleted";
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackCancelledOrder", commandKey = "cancelOrderServiceCommand")
	@Transactional
	public String cancelOrder(int orderId) {
		OrderEntity cancelledOrder = orderRepo.findById(orderId).get();
		cancelledOrder.setOrderStatus("Cancelled");
		orderRepo.save(cancelledOrder);
		fireOrderCancelledEvent(cancelledOrder);

		return "Cancelled";
	}

	private void fireOrderCancelledEvent(OrderEntity order) {
		kafkaTemplate.send("cancel", "cancelled", order);
	}

	private String fallbackCancelledOrder(int orderId) {
		OrderEntity cancelledOrder = orderRepo.getOne(orderId);
		cancelledOrder.setOrderStatus("Cancelled");
		orderRepo.save(cancelledOrder);
		return OrderConstant.cancelOrderPending;
	}
}
