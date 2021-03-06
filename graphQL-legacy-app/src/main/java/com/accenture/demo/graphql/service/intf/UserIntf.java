package com.accenture.demo.graphql.service.intf;

import java.util.List;

import com.accenture.demo.graphql.model.Address;
import com.accenture.demo.graphql.model.User;

public interface UserIntf {
	public List<User> getAllUsers();

	public User getUserById(int userId);

	public User addUser(String name, Address address);

	public String deleteUser(int userId);
}
