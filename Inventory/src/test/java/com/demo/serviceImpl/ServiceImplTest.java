package com.demo.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.inventory.dto.InventoryDto;
import com.inventory.entity.InventoryEntity;
import com.inventory.repo.InventoryRepo;
import com.inventory.serviceImpl.InventoryServiceImpl;

@RunWith(SpringRunner.class)
public class ServiceImplTest {
	@InjectMocks
	InventoryServiceImpl inventoryServiceImpl;
	@Mock
	InventoryRepo inventoryRepo;

	List<InventoryDto> listInventoryDto;
	InventoryDto inventoryDto;
	Optional<InventoryEntity> optionalInventoryEntity;
	List<InventoryEntity> listInventoryEntity;
	InventoryEntity inventoryEntity;

	@Before
	public void setup() {

		inventoryRepo = Mockito.mock(InventoryRepo.class);
		inventoryServiceImpl = new InventoryServiceImpl(inventoryRepo);
		inventoryEntity = new InventoryEntity();
		optionalInventoryEntity = Optional.of(inventoryEntity);
		listInventoryEntity = new ArrayList<InventoryEntity>();

	}

	@Test
	public void getInventoryById() {
		inventoryEntity.setProductId(Long.parseLong("101"));
		inventoryEntity.setInventoryQuantity(3);
		when(inventoryRepo.findById(Long.parseLong("101"))).thenReturn(optionalInventoryEntity);
		inventoryDto = inventoryServiceImpl.getInventoryById(Long.parseLong("101"));
		System.out.println("---------" + inventoryDto.getInventoryQuantity());
	}

	@Test
	public void getAllInventory() {
		InventoryEntity inventoryEntity = new InventoryEntity(101L, 3);
		InventoryEntity inventoryEntity1 = new InventoryEntity(101L, 3);
		listInventoryEntity.add(inventoryEntity);
		listInventoryEntity.add(inventoryEntity1);
		when(inventoryRepo.findAll()).thenReturn(listInventoryEntity);
		listInventoryDto = inventoryServiceImpl.getAllInventory();
	}

	@Test
	public void saveInventory() {
		InventoryEntity inventoryEntity = new InventoryEntity(101L, 3);
		when(inventoryRepo.save(inventoryEntity)).thenReturn(inventoryEntity);
		inventoryDto = new InventoryDto(inventoryEntity.getProductId(), inventoryEntity.getInventoryQuantity());
//		inventoryServiceImpl.saveInventory(inventoryDto);

		// assertEquals("101",inventoryDto.getProductId().toString());
	}

	@Test
	public void deleteOrderByIdNotDlt() {
		Mockito.when(inventoryRepo.existsById(Long.valueOf(102))).thenReturn(true, true);
		doNothing().when(inventoryRepo).deleteById(Long.valueOf(102));
		String result = inventoryServiceImpl.deleteInventory(Long.valueOf(102));
		assertEquals("Requested Inventory Id Not Deleted Succssfully :102", result);
	}

	@Test
	public void deleteOrderByIdDlt() {
		Mockito.when(inventoryRepo.existsById(Long.valueOf(102))).thenReturn(true, false);
		doNothing().when(inventoryRepo).deleteById(Long.valueOf(102));
		String result = inventoryServiceImpl.deleteInventory(Long.valueOf(102));
		assertEquals("Requested Inventory Id Deleted Succssfully :102", result);
	}

	@Test
	public void deleteOrderByIdNotFound() {
		Mockito.when(inventoryRepo.existsById(Long.valueOf(102))).thenReturn(false, false);
		doNothing().when(inventoryRepo).deleteById(Long.valueOf(102));
		String result = inventoryServiceImpl.deleteInventory(Long.valueOf(102));
		assertEquals("Requested Inventory Id Not found :102", result);
	}

	/*
	 * @Test public void getAllInventoryException() throws Exception {
	 * when(inventoryRepo.findAll()).thenReturn(null); listInventoryDto =
	 * inventoryServiceImpl.getAllInventory(); //
	 * assertEquals(2,listInventoryDto.size()); //
	 * assertEquals("3",listInventoryDto.get(0).getInventoryQuantity()); }
	 */

}
