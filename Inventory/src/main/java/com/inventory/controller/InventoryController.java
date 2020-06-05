package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.InventoryDto;
import com.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
	@GetMapping(path = "/getExample")
	public String getExample() {
		return "inventory-service";
	}
	
	@GetMapping(path = "/getAllInventory")
	public List<InventoryDto> getAllInventory() {
		return inventoryService.getAllInventory();
	}

	@GetMapping(path = "/getInventoryById/{id}")
	public InventoryDto getInventoryById(@PathVariable Long id) {
		return inventoryService.getInventoryById(id);
	}

	@PostMapping(path = "/saveInventory")
	public InventoryDto saveInventory(@RequestBody InventoryDto dto) {
		return inventoryService.saveInventory(dto);

	}

	@PutMapping(path = "/updateInventory/{id}")
	public InventoryDto updateInventory(@PathVariable Long id,
			@RequestBody InventoryDto dto) {
		return inventoryService.updateInventory(id, dto);

	}

	@DeleteMapping(path = "/deleteInventory/{id}")
	public String deleteInventory(@PathVariable Long id) {
		return inventoryService.deleteInventory(id);
	}

}
