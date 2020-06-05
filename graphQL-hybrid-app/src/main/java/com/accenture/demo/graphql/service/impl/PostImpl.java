package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.legacy.rest.PostRest;
import com.accenture.demo.graphql.legacy.rest.UserRest;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.model.PostInput;
import com.accenture.demo.graphql.service.intf.PostIntf;

@Service
public class PostImpl implements PostIntf {

	@Autowired
	private PostRest postRest;

	@Autowired
	private UserRest userRest;

	/**
	 * Call class that will trigger rest call to get list of post by a specific user
	 * Method to activate sublist base on top value and returned data size
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<Post> getPosts(int userId, int top) {
		// TODO Auto-generated method stub
		List<Post> posts = null;
		try {
			posts = postRest.getPostsByUserId(userId);
			if (posts.size() > top && top > 0) {
				posts = posts.subList(0, top);
			}
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in post service processing", e.toString());
		}
		return posts;
	}

	/**
	 * Call class that will trigger rest call to create a new post by a specific
	 * user Call class that will trigger rest call to retrieve post creator user
	 * details
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public User addPost(int userId, PostInput postInput) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			Post post = postRest.addPost(userId, postInput);
			if (post == null) {
				throw new GraphQLException("Error in post service processing", "Add of post returned null");
			}
			user = userRest.getUserById(userId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in post service processing", e.toString());
		}
		return user;
	}

	/**
	 * Call class that will trigger rest call to update a post by a specific user
	 * Call class that will trigger rest call to retrieve post updater user details
	 * Error in method will capture only for GraphQLException from Rest Class and Unknown Exception
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public User updatePost(int userId, int postId, PostInput postInput) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String post = postRest.updatePost(userId, postId, postInput);
			// To add failure handler later
			if (post == null) {
				throw new GraphQLException("Error in post service processing", "Update of post returned null");
			}
			user = userRest.getUserById(userId);

		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in post service processing", e.toString());
		}

		return user;
	}

	/**
	 * Call class that will trigger rest call to delete a specific post by a
	 * specific user Call class that will trigger rest call to retrieve post deleter
	 * user details
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public User deletePost(int userId, int postId) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String post = postRest.deletePost(userId, postId);
			// To add failure handler later
			if (post == null) {
				throw new GraphQLException("Error in post service processing", "Delete of post returned null");
			}
			user = userRest.getUserById(userId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in post service processing", e.toString());
		}
		return user;
	}

}
