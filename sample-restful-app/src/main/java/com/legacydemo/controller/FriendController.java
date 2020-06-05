package com.legacydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.legacydemo.model.Friend;
import com.legacydemo.service.intf.FriendIntf;
/**
 * 
 * @author yallaiah.eswar
 *
 */

@RestController
public class FriendController {
	
	@Autowired
	private FriendIntf friendService;
	
	@GetMapping("/profile/{userId}/friends")
	public List<Friend> getUserFriends(@PathVariable("userId") Integer userId) {
		return friendService.getUserFriends(userId);
	}
	
}
