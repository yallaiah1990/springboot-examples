package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.legacy.rest.CommentRest;
import com.demo.graphql.legacy.rest.PostRest;
import com.demo.graphql.model.Comment;
import com.demo.graphql.model.CommentInput;
import com.demo.graphql.model.Post;
import com.demo.graphql.service.intf.CommentIntf;

/**
 *  @author yallaiah.eswar
 */

@Service
public class CommentImpl implements CommentIntf {

	@Autowired
	private CommentRest commentRest;
	
	@Autowired
	private PostRest postRest;
	
	/**
	 * Call class that will trigger rest call to get list of comments on a specific post
	 * Method to activate sublist base on if top value and returned data size
	 */
	@Override
	public List<Comment> getComments(int postId, int top) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRest.getComments(postId);
		if(comments.size() > top && top > 0) {
			comments = comments.subList(0, top);
		}
		return comments;
	}
	
	/**
	 * Call class that will trigger rest call to create a new comment on a specific post
	 * Call class that will trigger rest call to retrieve post details where comment was added
	 */
	@Override
	public Post addComment(int postId, CommentInput commentInput) {
		// TODO Auto-generated method stub
		Comment comment = commentRest.addComment(postId, commentInput);
		//To add failure handler later
		if(comment == null) {
			return null;
		}
		
		return postRest.getPostById(postId);
	}
	
	/**
	 * Call class that will trigger rest call to update a comment on a specific post
	 * Call class that will trigger rest call to retrieve post details where comment was updated
	 */
	@Override
	public Post updateComment(int postId, int commentId, CommentInput commentInput) {
		// TODO Auto-generated method stub
		String comment = commentRest.updateComment(postId, commentId, commentInput);
		//To add failure handler later
		if(comment == null) {
			return null;
		}
		return postRest.getPostById(postId);
	}
	
	/**
	 * Call class that will trigger rest call to delete a comment on a specific post
	 * Call class that will trigger rest call to retrieve post details where comment was deleted
	 */
	@Override
	public Post deleteComment(int postId, int commentId) {
		// TODO Auto-generated method stub
		String comment = commentRest.deleteComment(postId, commentId);
		//To add failure handler later
		if(comment == null) {
			return null;
		}
		return postRest.getPostById(postId);
	}
}
