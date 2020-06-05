package com.accenture.demo.graphql.legacy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.model.PostInput;

@Component
public class PostRest {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${demo.service.url}")
	private String externalUrl;

	@Value("${demo.service.uri.userPosts}")
	private String externalURIUserPosts;

	@Value("${demo.service.uri.userPostById}")
	private String externalURIUserByPostId;

	@Value("${demo.service.uri.postById}")
	private String externalURIPostById;

	/**
	 * Function to call rest backend to get list of posts of a specific user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @return
	 */
	public List<Post> getPostsByUserId(int userId) {
		List<Post> posts = null;
		try {
			ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(
					externalUrl + externalURIUserPosts.replaceAll("\\{userId\\}", String.valueOf(userId)),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
					});

			posts = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling posts backend", e.toString());
		}
		return posts;
	}

	/**
	 * Function to call rest backend to get specific posts details
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param postId
	 * @return
	 */
	public Post getPostById(int postId) {
		Post post = null;
		try {
			ResponseEntity<Post> responseEntity = restTemplate.exchange(
					externalUrl + externalURIPostById.replaceAll("\\{postId\\}", String.valueOf(postId)),
					HttpMethod.GET, null, new ParameterizedTypeReference<Post>() {
					});

			post = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling posts backend", e.toString());
		}
		return post;
	}

	/**
	 * Function to call rest backend to add a post under a specific user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @param postInput
	 * @return
	 */
	public Post addPost(int userId, PostInput postInput) {
		Post post = null;
		try {
			Post newPost = new Post();
			newPost.setContent(postInput.getContent());
			post = restTemplate.postForObject(
					externalUrl + externalURIUserPosts.replaceAll("\\{userId\\}", String.valueOf(userId)), newPost,
					Post.class);
		} catch (Exception e) {
			throw new GraphQLException("Error in calling posts backend", e.toString());
		}
		return post;
	}

	/**
	 * Function to call rest backend to update a post under a specific user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @param postId
	 * @param postInput
	 * @return
	 */
	public String updatePost(int userId, int postId, PostInput postInput) {
		ResponseEntity<String> response = null;
		try {
			String externalURIUserByPostId = this.externalURIUserByPostId.replaceAll("\\{userId\\}",
					String.valueOf(userId));
			externalURIUserByPostId = externalURIUserByPostId.replaceAll("\\{postId\\}", String.valueOf(postId));
			Post updatedPost = new Post();
			updatedPost.setId(postId);
			updatedPost.setContent(postInput.getContent());

			HttpEntity<Post> requestEntity = new HttpEntity<>(updatedPost, null);
			response = restTemplate.exchange(externalUrl + externalURIUserByPostId, HttpMethod.PUT, requestEntity,
					String.class);
		} catch (Exception e) {
			throw new GraphQLException("Error in calling posts backend", e.toString());
		}
		return response.getBody();
	}

	/**
	 * Function to call rest backend to delete a post under a specific user
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param userId
	 * @param postId
	 * @return
	 */
	public String deletePost(int userId, int postId) {
		String message = null;
		try {
			String externalURIUserByPostId = this.externalURIUserByPostId.replaceAll("\\{userId\\}",
					String.valueOf(userId));
			externalURIUserByPostId = externalURIUserByPostId.replaceAll("\\{postId\\}", String.valueOf(postId));
			ResponseEntity<String> response = restTemplate.exchange(externalUrl + externalURIUserByPostId,
					HttpMethod.DELETE, null, String.class);
			message = response.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling posts backend", e.toString());
		}
		return message;
	}
}
