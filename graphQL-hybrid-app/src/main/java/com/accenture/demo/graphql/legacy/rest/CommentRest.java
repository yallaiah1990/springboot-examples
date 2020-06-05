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
import com.accenture.demo.graphql.model.Comment;
import com.accenture.demo.graphql.model.CommentInput;

@Component
public class CommentRest {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${demo.service.url}")
	private String externalUrl;

	@Value("${demo.service.uri.postComments}")
	private String externalURIPostComments;

	@Value("${demo.service.uri.postCommentById}")
	private String externalURIPostCommentById;

	/**
	 * Function to call rest backend to get list of comments under a specific post
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param postId
	 * @return
	 */
	public List<Comment> getComments(int postId) {
		List<Comment> comments = null;
		try {
			ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(
					externalUrl + externalURIPostComments.replaceAll("\\{postId\\}", String.valueOf(postId)),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
					});

			comments = responseEntity.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling comments backend", e.toString());
		}
		return comments;
	}

	/**
	 * Function to call rest backend to add a comment under a specific post
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param postId
	 * @param commentInput
	 * @return
	 */
	public Comment addComment(int postId, CommentInput commentInput) {
		Comment comment = null;
		try {
			Comment newComment = new Comment();
			newComment.setContent(commentInput.getContent());
			newComment.setName(commentInput.getName());
			comment = restTemplate.postForObject(
					externalUrl + externalURIPostComments.replaceAll("\\{postId\\}", String.valueOf(postId)),
					newComment, Comment.class);
		} catch (Exception e) {
			throw new GraphQLException("Error in calling comments backend", e.toString());
		}
		return comment;
	}

	/**
	 * Function to call rest backend to update a specific comment under a post
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param postId
	 * @param commentId
	 * @param commentInput
	 * @return
	 */
	public String updateComment(int postId, int commentId, CommentInput commentInput) {
		String message = null;
		try {
			String externalURIPostCommentById = this.externalURIPostCommentById.replaceAll("\\{postId\\}",
					String.valueOf(postId));
			externalURIPostCommentById = externalURIPostCommentById.replaceAll("\\{commentId\\}",
					String.valueOf(commentId));
			Comment updatedComment = new Comment();
			updatedComment.setId(commentId);
			updatedComment.setContent(commentInput.getContent());
			updatedComment.setName(commentInput.getName());
			HttpEntity<Comment> requestEntity = new HttpEntity<>(updatedComment, null);
			ResponseEntity<String> response = restTemplate.exchange(externalUrl + externalURIPostCommentById,
					HttpMethod.PUT, requestEntity, String.class);
			message = response.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling comments backend", e.toString());
		}
		return message;
	}

	/**
	 * Function to call rest backend delete a specific comment under a post
	 * Capture any exception from doing a rest call and throw GraphQLException
	 * 
	 * @param postId
	 * @param commentId
	 * @return
	 * 
	 */
	public String deleteComment(int postId, int commentId) {
		String message = null;
		try {
			String externalURIPostCommentById = this.externalURIPostCommentById.replaceAll("\\{postId\\}",
					String.valueOf(postId));
			externalURIPostCommentById = externalURIPostCommentById.replaceAll("\\{commentId\\}",
					String.valueOf(commentId));
			ResponseEntity<String> response = restTemplate.exchange(externalUrl + externalURIPostCommentById,
					HttpMethod.DELETE, null, String.class);
			message = response.getBody();
		} catch (Exception e) {
			throw new GraphQLException("Error in calling comments backend", e.toString());
		}
		return message;
	}
}
