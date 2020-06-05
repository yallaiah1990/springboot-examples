package com.accenture.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.legacydemo.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
