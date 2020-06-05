package com.orderHistory.response;

import java.util.Date;

import javax.persistence.EmbeddedId;

import com.orderHistory.entity.OrderId;

public class OrderHistoryResponse {
	@EmbeddedId
	private OrderId id;
	
   	private String orderQuantity;
	private Long productId;
	private Date orderedDate;
	private String orderStatus;
	private String orderedProduct;
	private Date orderUpdatedDate;
	private String orderedBy;
	
	

	public OrderHistoryResponse() {

	}



	public OrderId getId() {
		return id;
	}

	public void setId(OrderId id) {
		this.id = id;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(String orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getOrderUpdatedDate() {
		return orderUpdatedDate;
	}

	public void setOrderUpdatedDate(Date orderUpdatedDate) {
		this.orderUpdatedDate = orderUpdatedDate;
	}

	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
}
