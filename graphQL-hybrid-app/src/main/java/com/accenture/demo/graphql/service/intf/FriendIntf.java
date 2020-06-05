package com.accenture.demo.graphql.service.intf;

import java.util.List;

import com.accenture.demo.graphql.model.Friend;

public interface FriendIntf {
	public List<Friend> getFriends(int userId, int top);
}
