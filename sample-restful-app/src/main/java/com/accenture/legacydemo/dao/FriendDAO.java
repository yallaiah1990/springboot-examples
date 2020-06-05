package com.accenture.legacydemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.legacydemo.model.Friend;
import com.accenture.legacydemo.repository.FriendRepository;

@Component
public class FriendDAO {
	
	@Autowired
	private FriendRepository friendRepository;
	
	public List<Friend> getUserFriends(Integer userId) {
		List<Friend> friendFound = friendRepository.findByUserId(userId);
		return friendFound;
	}
}
