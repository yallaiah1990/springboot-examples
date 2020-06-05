package com.orderHistory.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderId implements Serializable{
	
	private Long orderId;
	
	@Column(name = "order_timestamp", nullable = false, unique = true)
	private Timestamp orderTimestamp;

	public OrderId() {
		
	}
	public OrderId(Long orderId, Timestamp orderTimestamp) {
		super();
		this.orderId = orderId;
		this.orderTimestamp = orderTimestamp;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Timestamp getOrderTimestamp() {
		return orderTimestamp;
	}
	public void setOrderTimestamp(Timestamp orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	
	
	
	
}
