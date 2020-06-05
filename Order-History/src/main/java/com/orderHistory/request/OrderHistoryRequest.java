package com.orderHistory.request;

public class OrderHistoryRequest {
	private String orderId;
	private String productId;
	private String OrderedDate;
	private String OrderedProductName;
	private String customerId;

	public String getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public String getOrderedDate() {
		return OrderedDate;
	}

	public String getOrderedProductName() {
		return OrderedProductName;
	}

	public String getCustomerId() {
		return customerId;
	}

}
