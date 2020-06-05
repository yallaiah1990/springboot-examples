package com.legacydemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legacydemo.model.AuthData;
import com.legacydemo.model.User;
import com.legacydemo.repository.AuthDataRepository;
import com.legacydemo.repository.UserRepository;

@Component
public class UserDAO {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthDataRepository authDataRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Integer userId) {
		Optional<User> userFound = userRepository.findById(userId);
		if (!userFound.isPresent()) {
			return null;
		}
		return userFound;
	}

	public User addUser(User user) {
		userRepository.save(user);
		return user;
	}

	public String deleteUser(Integer userId) {
		Optional<User> userFound = userRepository.findById(userId);
		if (userFound.isPresent()) {
			User user = userRepository.getOne(userId);
			userRepository.delete(user);
			return "Successfully deleted " + user.getName();
		}

		return "User not found";
	}
	
	public AuthData userSignin(String email) {
		AuthData auth = authDataRepository.findByEmail(email);
		return auth;
	}
}
