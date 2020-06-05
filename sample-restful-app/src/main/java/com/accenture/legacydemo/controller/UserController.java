package com.accenture.legacydemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.legacydemo.model.AuthData;
import com.accenture.legacydemo.model.User;
import com.accenture.legacydemo.service.intf.UserIntf;

/**
 * 
 * @author yallaiah.eswar
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserIntf userService;

	@GetMapping("/profile")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/profile/{userId}")
	public Optional<User> getUser(@PathVariable("userId") Integer userId) {
		return userService.getUser(userId);
	}

	@GetMapping("/loginAuth/{email}")
	public AuthData userSignin(@PathVariable("email") String email) {
		return userService.userSignin(email);
	}

	@PostMapping("/profile")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@DeleteMapping("/profile/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		return userService.deleteUser(userId);
	}
}
