package com.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.inventory.controller.InventoryController;
import com.inventory.dto.InventoryDto;
import com.inventory.service.InventoryService;

@RunWith(SpringRunner.class)
public class InventoryControllerTest {

	@InjectMocks
	InventoryController inventoryController;

	@Mock
	InventoryService inventoryService;

	List<InventoryDto> listInventoryDto;
	InventoryDto inventoryDto;
	InventoryDto inventoryDto1;

	@Before
	public void setup() {
		inventoryDto = new InventoryDto();
		inventoryDto1 = new InventoryDto();
		listInventoryDto = new ArrayList<InventoryDto>();
		inventoryDto.setProductId(Long.parseLong("101"));
		inventoryDto.setInventoryQuantity(3);
		inventoryDto1.setProductId(Long.parseLong("102"));
		inventoryDto1.setInventoryQuantity(5);
		listInventoryDto.add(inventoryDto);
		listInventoryDto.add(inventoryDto1);
	}

	@Test
	public void getAllInventory() {
		when(inventoryService.getAllInventory()).thenReturn(listInventoryDto);
		listInventoryDto = inventoryController.getAllInventory();
		 assertEquals(2,listInventoryDto.size());
		 assertEquals("3",listInventoryDto.get(0).getInventoryQuantity().toString());
	}

	@Test
	public void getInventoryById() {
		when(inventoryService.getInventoryById(Long.parseLong("101"))).thenReturn(inventoryDto);
		inventoryDto = inventoryController.getInventoryById(Long.valueOf(101));
		assertEquals("101",inventoryDto.getProductId().toString());
	}

	@Test
	public void saveInventory() {
		
		when(inventoryService.saveInventory(inventoryDto)).thenReturn(inventoryDto);
		inventoryDto = inventoryController.saveInventory(inventoryDto);
		assertThat(inventoryDto.getInventoryQuantity() != null);
	}

	@Test
	public void updateInventory() {
		inventoryDto.setInventoryQuantity(100);
		when(inventoryService.updateInventory(Long.parseLong("101"), inventoryDto)).thenReturn(inventoryDto);
		inventoryDto = inventoryController.updateInventory(Long.parseLong("101"), inventoryDto);
		assertEquals("100",inventoryDto.getInventoryQuantity().toString());
	}

	@Test
	public void deleteOrderByIdElse() {
		when(inventoryService.deleteInventory(Long.parseLong("101")))
				.thenReturn("Requested Inventory Id Deleted Succssfully :" + 101);
		String result = inventoryController.deleteInventory(Long.parseLong("101"));
		assertEquals("Requested Inventory Id Deleted Succssfully :" + 101, result);
	}
}
