package com.accenture.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.legacydemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
