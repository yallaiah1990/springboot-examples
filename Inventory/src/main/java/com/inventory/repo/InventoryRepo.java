package com.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.InventoryEntity;

public interface InventoryRepo extends JpaRepository<InventoryEntity, Long>{

}
