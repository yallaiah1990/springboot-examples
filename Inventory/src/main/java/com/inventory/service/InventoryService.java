package com.inventory.service;

import java.util.List;

import com.inventory.dto.InventoryDto;

public interface InventoryService {

	List<InventoryDto> getAllInventory();

	InventoryDto getInventoryById(Long id);

	InventoryDto saveInventory(InventoryDto dto);

	InventoryDto updateInventory(Long id, InventoryDto dto);

	String deleteInventory(Long id);

}
