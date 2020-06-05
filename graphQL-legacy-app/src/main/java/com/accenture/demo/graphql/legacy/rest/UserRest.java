package com.accenture.demo.graphql.legacy.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.demo.graphql.model.Address;
import com.accenture.demo.graphql.model.User;

@Component
public class UserRest {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${demo.service.url}")
	private String externalUrl;

	@Value("${demo.service.uri.user}")
	private String externalURIUser;

	@Value("${demo.service.uri.userById}")
	private String externalURIUserById;

	/**
	 * Function to call rest backend to get list of users
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();

		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(externalUrl + externalURIUser, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<User>>() {
				});

		userList = responseEntity.getBody();

		return userList;
	}

	/**
	 * Function to call rest backend to get specific user details
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		User user = new User();

		ResponseEntity<User> responseEntity = restTemplate.exchange(
				externalUrl + externalURIUserById.replaceAll("\\{userId\\}", String.valueOf(userId)), HttpMethod.GET,
				null, new ParameterizedTypeReference<User>() {
				});

		user = responseEntity.getBody();

		return user;
	}

	/**
	 * Function to call rest backend to add a user
	 * 
	 * @param name
	 * @param address
	 * @return
	 */
	public User addUser(String name, Address address) {
		User newUser = new User();
		newUser.setName(name);
		newUser.setAddress(address);
		User user = restTemplate.postForObject(externalUrl + externalURIUser, newUser, User.class);
		return user;
	}

	/**
	 * Function to call rest backend to delete a user
	 * 
	 * @param userId
	 * @return
	 */

	public String deleteUser(int userId) {
		ResponseEntity<String> response = restTemplate.exchange(
				externalUrl + externalURIUserById.replaceAll("\\{userId\\}", String.valueOf(userId)), HttpMethod.DELETE,
				null, String.class);
		return response.getBody();
	}
}
