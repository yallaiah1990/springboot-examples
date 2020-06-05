package com.accenture.demo.graphql.legacy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.Friend;

@Component
public class FriendRest {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${demo.service.url}")
	private String externalUrl;

	@Value("${demo.service.uri.userFriends}")
	private String externalURIUserFriends;

	/**
	 * Function to call rest backend to get list of friends of a specific user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @return
	 */
	public List<Friend> getFriends(int userId) {
		List<Friend> friends = null;
		try {
			ResponseEntity<List<Friend>> responseEntity = restTemplate.exchange(
					externalUrl + externalURIUserFriends.replaceAll("\\{userId\\}", String.valueOf(userId)),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Friend>>() {
					});

			friends = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling friends backend", e.toString());
		}
		return friends;
	}
}
