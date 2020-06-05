package com.accenture.legacydemo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.legacydemo.dao.FriendDAO;
import com.accenture.legacydemo.model.Friend;
import com.accenture.legacydemo.service.intf.FriendIntf;

@Service
public class FriendImpl implements FriendIntf{

	@Autowired
	private FriendDAO friendDAO;
	
	@Override
	public List<Friend> getUserFriends(Integer userId) {
		// TODO Auto-generated method stub
		return friendDAO.getUserFriends(userId);
	}

}
