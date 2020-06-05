package com.legacydemo.service.intf;

import java.util.List;

import com.legacydemo.model.Friend;

public interface FriendIntf {
	public List<Friend> getUserFriends(Integer userId);
}
