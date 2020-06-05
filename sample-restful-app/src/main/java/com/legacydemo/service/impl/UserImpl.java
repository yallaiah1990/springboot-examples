package com.legacydemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legacydemo.dao.UserDAO;
import com.legacydemo.model.AuthData;
import com.legacydemo.model.User;
import com.legacydemo.service.intf.UserIntf;

@Service
public class UserImpl implements UserIntf {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDAO.getUsers();
	}

	@Override
	public Optional<User> getUser(Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	@Override
	public String deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(userId);
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.addUser(user);
	}

	@Override
	public AuthData userSignin(String email) {
		// TODO Auto-generated method stub
		return userDAO.userSignin(email);
	}

}
