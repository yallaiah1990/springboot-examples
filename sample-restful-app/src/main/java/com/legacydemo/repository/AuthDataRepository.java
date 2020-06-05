package com.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.AuthData;

public interface AuthDataRepository extends JpaRepository <AuthData, Integer>{

	 public AuthData findByEmail(String email);
}
