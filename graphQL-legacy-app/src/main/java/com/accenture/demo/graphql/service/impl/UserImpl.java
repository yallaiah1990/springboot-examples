package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.legacy.rest.UserRest;
import com.accenture.demo.graphql.model.Address;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.UserIntf;


/**
 * 
 * @author yallaiah.eswar
 *
 */
@Service
public class UserImpl implements UserIntf {
	
	@Autowired
	private UserRest userRest;

	/**
	 * Call class that will trigger rest call to get list of users
	 */
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRest.getAllUsers();
	}

	/**
	 * Call class that will trigger rest call to get specific user details
	 */
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userRest.getUserById(userId);
	}

	/**
	 * Call class that will trigger creation of new user
	 */
	@Override
	public User addUser(String name, Address address) {
		// TODO Auto-generated method stub
		return userRest.addUser(name, address);
	}

	/**
	 * Call class that will trigger rest call to delete existing user
	 */
	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		return userRest.deleteUser(userId);
	}

}
