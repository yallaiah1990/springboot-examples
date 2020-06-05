package com.orderHistory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistoryEntity {
	//@Id 
	//@Column(name = "id", updatable = false, nullable = false)
	//long id;
	@EmbeddedId
	private OrderId id;
	
   	private String orderQuantity;
	private Long productId;
	private Date orderedDate;
	private String orderStatus;
	private String orderedProduct;
	private Date orderUpdatedDate;
	private String orderedBy;

 

	
	
		
		
	

	

	

	

	public OrderHistoryEntity(OrderId id, String orderQuantity, Long productId, Date orderedDate,
			String orderStatus, String orderedProduct, Date orderUpdatedDate, String orderedBy) {
		super();
		this.id = id;
		this.orderQuantity = orderQuantity;
		this.productId = productId;
		this.orderedDate = orderedDate;
		this.orderStatus = orderStatus;
		this.orderedProduct = orderedProduct;
		this.orderUpdatedDate = orderUpdatedDate;
		this.orderedBy = orderedBy;
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

	public OrderHistoryEntity() {

	}
}
