package com.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Long>{

}
