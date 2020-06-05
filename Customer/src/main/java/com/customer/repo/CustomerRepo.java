package com.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long>{

}
