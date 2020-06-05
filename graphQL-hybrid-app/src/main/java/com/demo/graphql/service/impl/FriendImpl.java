package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.legacy.rest.FriendRest;
import com.demo.graphql.model.Friend;
import com.demo.graphql.service.intf.FriendIntf;

@Service
public class FriendImpl implements FriendIntf {

	@Autowired
	private FriendRest friendRest;

	/**
	 * Call class that will trigger rest call to get list of friends by a specific
	 * user Method to activate sublist base on top value and returned data size
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<Friend> getFriends(int userId, int top) {
		// TODO Auto-generated method stub
		List<Friend> friends = null;
		try {
			friends = friendRest.getFriends(userId);
			if (friends.size() > top) {
				friends = friends.subList(0, top);
			}
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in friend service processing", e.toString());
		}
		return friends;
	}

}
