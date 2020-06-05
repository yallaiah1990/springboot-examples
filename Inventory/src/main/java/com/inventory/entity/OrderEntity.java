package com.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

public class OrderEntity {

	private Long orderId;
	private Long orderQuantity;
	private Long productId;
	private Date orderedDate;
	private String orderStatus;
	private String orderedProduct;
	private Date orderUpdatedDate;
	private String orderedBy;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Long orderQuantity) {
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

	public OrderEntity() {

	}
}
