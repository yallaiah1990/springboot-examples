package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.legacy.rest.FriendRest;
import com.demo.graphql.model.Friend;
import com.demo.graphql.service.intf.FriendIntf;


/**
 * 
 * @author yallaiah.eswar
 *
 */
@Service
public class FriendImpl implements FriendIntf {

	@Autowired
	private FriendRest friendRest;
	
	/**
	 * Call class that will trigger rest call to get list of friends by a specific user
	 * Method to activate sublist base on top value and returned data size
	 */
	@Override
	public List<Friend> getFriends(int userId, int top) {
		// TODO Auto-generated method stub
		List<Friend> friends = friendRest.getFriends(userId);
		if(friends.size() > top) {
			friends = friends.subList(0, top);
		}
		return friends;
	}

}
