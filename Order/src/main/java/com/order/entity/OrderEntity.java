package com.order.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderEntity {
	@Id
	@GeneratedValue
	private int orderId;
	private int orderQuantity;
	private int productId;
	private Date orderedDate;
	private String orderStatus;
	private String orderedProduct;
	private Date orderUpdatedDate;
	private String orderedBy;
	@Column(name = "order_timestamp", nullable = false, columnDefinition = "TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp orderTimestamp;

	public Timestamp getOrderTimestamp() {
		return orderTimestamp;
	}

	public void setOrderTimestamp(Timestamp orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
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


	public OrderEntity(int orderQuantity, int productId, String orderedBy) {
		this.orderQuantity = orderQuantity;
		this.productId = productId;
		this.orderedBy = orderedBy;
	}

	public OrderEntity() {

	}
}
