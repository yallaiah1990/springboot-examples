package com.demo.graphql.legacy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.Address;
import com.demo.graphql.model.User;

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
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> users = null;

		try {

			ResponseEntity<List<User>> responseEntity = restTemplate.exchange(externalUrl + externalURIUser,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
					});

			users = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling users backend", e.toString());
		}
		return users;
	}

	/**
	 * Function to call rest backend to get specific user details
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		User user = null;
		try {
			ResponseEntity<User> responseEntity = restTemplate.exchange(
					externalUrl + externalURIUserById.replaceAll("\\{userId\\}", String.valueOf(userId)),
					HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
					});

			user = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling users backend", e.toString());
		}
		return user;
	}

	/**
	 * Function to call rest backend to add a user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param name
	 * @param address
	 * @return
	 */
	public User addUser(String name, Address address) {
		User user = null;
		try {
			User newUser = new User();
			newUser.setName(name);
			newUser.setAddress(address);
			user = restTemplate.postForObject(externalUrl + externalURIUser, newUser, User.class);
		} catch (Exception e) {
			throw new GraphQLException("Error in calling users backend", e.toString());
		}
		return user;
	}

	/**
	 * Function to call rest backend to delete a user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @return
	 */

	public String deleteUser(int userId) {
		String message = null;
		try {
			ResponseEntity<String> response = restTemplate.exchange(
					externalUrl + externalURIUserById.replaceAll("\\{userId\\}", String.valueOf(userId)),
					HttpMethod.DELETE, null, String.class);
			message = response.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling users backend", e.toString());
		}
		return message;
	}
}
