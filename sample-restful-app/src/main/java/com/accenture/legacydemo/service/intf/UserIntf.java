package com.accenture.legacydemo.service.intf;

import java.util.List;
import java.util.Optional;

import com.accenture.legacydemo.model.AuthData;
import com.accenture.legacydemo.model.User;

public interface UserIntf {
	public List<User> getUsers();

	public Optional<User> getUser(Integer userId);

	public String deleteUser(Integer userId);

	public User addUser(User user);

	public AuthData userSignin(String email);
}
