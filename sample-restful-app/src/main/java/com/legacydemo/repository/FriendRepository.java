package com.legacydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
	
	public List<Friend> findByUserId(int userId);
}
