package com.accenture.legacydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.legacydemo.model.AuthData;

public interface AuthDataRepository extends JpaRepository <AuthData, Integer>{

	 public AuthData findByEmail(String email);
}
