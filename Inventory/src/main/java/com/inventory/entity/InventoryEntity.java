package com.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY_DETAILS")
public class InventoryEntity {
	@Id
	private Long productId;
	private Integer inventoryQuantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long inventoryId) {
		this.productId = inventoryId;
	}

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public InventoryEntity() {
		super();
	}

	public InventoryEntity(Long productId, Integer inventoryQuantity) {
		super();
		this.productId = productId;
		this.inventoryQuantity = inventoryQuantity;
	}

}
