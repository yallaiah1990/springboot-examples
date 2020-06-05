package com.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
