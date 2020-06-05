package com.order.dto;

public class InventoryDto {

	private Long productId;
	private Integer inventoryQuantity;

	public InventoryDto() {
	}

	public InventoryDto(Long productId, Integer inventoryQuantity) {
		this.productId = productId;
		this.inventoryQuantity = inventoryQuantity;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	@Override
	public String toString() {
		return "InventoryDto [productId=" + productId + ", inventoryQuantity=" + inventoryQuantity + "]";
	}
	
	
}
