package com.accenture.legacydemo.service.intf;

import java.util.List;

import com.accenture.legacydemo.model.Friend;

public interface FriendIntf {
	public List<Friend> getUserFriends(Integer userId);
}
