package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.legacy.rest.CommentRest;
import com.accenture.demo.graphql.legacy.rest.PostRest;
import com.accenture.demo.graphql.model.Comment;
import com.accenture.demo.graphql.model.CommentInput;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.service.intf.CommentIntf;

@Service
public class CommentImpl implements CommentIntf {

	@Autowired
	private CommentRest commentRest;

	@Autowired
	private PostRest postRest;

	/**
	 * Call class that will trigger rest call to get list of comments on a specific
	 * post Method to activate sublist base on if top value and returned data size
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<Comment> getComments(int postId, int top) {
		// TODO Auto-generated method stub
		List<Comment> comments = null;
		try {
			comments = commentRest.getComments(postId);
			if (comments.size() > top && top > 0) {
				comments = comments.subList(0, top);
			}
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in comment service processing", e.toString());
		}
		return comments;
	}

	/**
	 * Call class that will trigger rest call to create a new comment on a specific
	 * post Call class that will trigger rest call to retrieve post details where
	 * comment was added
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public Post addComment(int postId, CommentInput commentInput) {
		// TODO Auto-generated method stub
		Post post = null;
		try {
			Comment comment = commentRest.addComment(postId, commentInput);
			// To add failure handler later
			if (comment == null) {
				throw new GraphQLException("Error in comment service processing", "delete of comment returned null");
			}
			post = postRest.getPostById(postId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in comment service processing", e.toString());
		}
		return post;
	}

	/**
	 * Call class that will trigger rest call to update a comment on a specific post
	 * Call class that will trigger rest call to retrieve post details where comment
	 * was updated
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public Post updateComment(int postId, int commentId, CommentInput commentInput) {
		// TODO Auto-generated method stub
		Post post = null;
		try {
			String comment = commentRest.updateComment(postId, commentId, commentInput);
			// To add failure handler later
			if (comment == null) {
				throw new GraphQLException("Error in comment service processing", "delete of comment returned null");
			}
			post = postRest.getPostById(postId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in comment service processing", e.toString());
		}

		return post;
	}

	/**
	 * Call class that will trigger rest call to delete a comment on a specific post
	 * Call class that will trigger rest call to retrieve post details where comment
	 * was deleted
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public Post deleteComment(int postId, int commentId) {
		// TODO Auto-generated method stub
		Post post = null;
		try {
			String comment = commentRest.deleteComment(postId, commentId);
			// To add failure handler later
			if (comment == null) {
				throw new GraphQLException("Error in comment service processing", "delete of comment returned null");
			}
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in comment service processing", e.toString());
		}
		post = postRest.getPostById(postId);
		return post;
	}
}
