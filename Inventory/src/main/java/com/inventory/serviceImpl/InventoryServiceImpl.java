package com.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.dto.InventoryDto;
import com.inventory.entity.InventoryEntity;
import com.inventory.exception.InventoryNotFoundException;
import com.inventory.repo.InventoryRepo;
import com.inventory.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepo inventoryRepo;

	public InventoryServiceImpl(InventoryRepo inventoryRepo) {
	this.inventoryRepo=inventoryRepo;
	}

	@Override
	public List<InventoryDto> getAllInventory() {
		List<InventoryDto> dtoList = new ArrayList<InventoryDto>();
		List<InventoryEntity> result = inventoryRepo.findAll();
		if(result.size()>0 && result!=null) {
			for (InventoryEntity inventoryEntity : result) {
				InventoryDto dto = domainToDto(inventoryEntity);
				dtoList.add(dto);
			}
		}else {
			throw new InventoryNotFoundException("Data not found");
		}
		
		return dtoList;
	}

	@Override
	public InventoryDto getInventoryById(Long id){
		InventoryDto dto = new InventoryDto();
		try {
			Optional<InventoryEntity> inventoryEntity = inventoryRepo.findById(id);
			dto.setProductId(inventoryEntity.get().getProductId());
			dto.setInventoryQuantity(inventoryEntity.get().getInventoryQuantity());
		} catch (Exception e) {
			//throw new InventoryNotFoundException(InventoryConstant.inventoryMsg);
			return dto;
			 
		}
		return dto;
	}

	@Override
	public InventoryDto saveInventory(InventoryDto dto) {
		InventoryEntity entity =dtoToDomain(dto);				
		InventoryEntity entity1=inventoryRepo.save(entity);
		InventoryDto response=domainToDto(entity1);
		return response;
	}

	@Override
	public InventoryDto updateInventory(Long id, InventoryDto dto) {
		Optional<InventoryEntity> result = inventoryRepo.findById(id);
		dto.setProductId(result.get().getProductId());
		InventoryDto responseDto = domainToDto(inventoryRepo.save(dtoToDomain(dto)));
		return responseDto;
	}

	@Override
	public String deleteInventory(Long id) {
		if (inventoryRepo.existsById(id)) {
			inventoryRepo.deleteById(id);
			if (inventoryRepo.existsById(id)) {
				return "Requested Inventory Id Not Deleted Succssfully :" + id;
			} else {
				return "Requested Inventory Id Deleted Succssfully :" + id;
			}
		} else {
			return "Requested Inventory Id Not found :" + id;
		}
	}

	private InventoryEntity dtoToDomain(InventoryDto dto) {
		InventoryEntity invEntity = new InventoryEntity();
		invEntity.setProductId(dto.getProductId());
		invEntity.setInventoryQuantity(dto.getInventoryQuantity());
		return invEntity;
	}

	private InventoryDto domainToDto(InventoryEntity invEntity) {
		InventoryDto dto = new InventoryDto();
		dto.setProductId(invEntity.getProductId());
		dto.setInventoryQuantity(invEntity.getInventoryQuantity());
		return dto;
	}
}
