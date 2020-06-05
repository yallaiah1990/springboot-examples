package com.demo.graphql.legacy.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.graphql.model.Comment;
import com.demo.graphql.model.CommentInput;


/**
 * 
 * @author yallaiah.eswar
 *
 */

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
	 * 
	 * @param postId
	 * @return
	 */
	public List<Comment> getComments(int postId) {
		List<Comment> commentList = new ArrayList<Comment>();

		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(
				externalUrl + externalURIPostComments.replaceAll("\\{postId\\}", String.valueOf(postId)),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Comment>>(){});
		
		commentList = responseEntity.getBody();
		return commentList;
	}
	
	/**
	 * Function to call rest backend to add a comment under a specific post
	 * 
	 * @param postId
	 * @param commentInput
	 * @return
	 */
	public Comment addComment(int postId, CommentInput commentInput){
		Comment newComment = new Comment();
		newComment.setContent(commentInput.getContent());
		newComment.setName(commentInput.getName());
		Comment comment = restTemplate.postForObject(externalUrl + externalURIPostComments.replaceAll("\\{postId\\}", String.valueOf(postId)), newComment, Comment.class);
		return comment;
	}
	
	/**
	 * Function to call rest backend to update a specific comment under a post
	 * 
	 * @param postId
	 * @param commentId
	 * @param commentInput
	 * @return
	 */
	public String updateComment(int postId, int commentId, CommentInput commentInput){
		String externalURIPostCommentById = this.externalURIPostCommentById.replaceAll("\\{postId\\}", String.valueOf(postId));
		externalURIPostCommentById = externalURIPostCommentById.replaceAll("\\{commentId\\}", String.valueOf(commentId));
		Comment updatedComment = new Comment();
		updatedComment.setId(commentId);
		updatedComment.setContent(commentInput.getContent());
		updatedComment.setName(commentInput.getName());
		HttpEntity<Comment> requestEntity = new HttpEntity<>(updatedComment,null);
		ResponseEntity<String> response = restTemplate.exchange(
				externalUrl + externalURIPostCommentById, HttpMethod.PUT,
				requestEntity, String.class);
		return response.getBody();
	}
	
	/**
	 * Function to call rest backend delete a specific comment under a post
	 * 
	 * @param postId
	 * @param commentId
	 * @return
	 * 
	 */
	public String deleteComment(int postId, int commentId){
		String externalURIPostCommentById = this.externalURIPostCommentById.replaceAll("\\{postId\\}", String.valueOf(postId));
		externalURIPostCommentById = externalURIPostCommentById.replaceAll("\\{commentId\\}", String.valueOf(commentId));
		ResponseEntity<String> response = restTemplate.exchange(
				externalUrl + externalURIPostCommentById, HttpMethod.DELETE,
				null, String.class);
		return response.getBody();
	}
}
