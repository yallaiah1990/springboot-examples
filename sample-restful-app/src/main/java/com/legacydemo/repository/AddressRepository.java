package com.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
