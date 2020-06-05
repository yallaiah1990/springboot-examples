package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.legacy.rest.PostRest;
import com.accenture.demo.graphql.legacy.rest.UserRest;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.model.PostInput;
import com.accenture.demo.graphql.service.intf.PostIntf;

/**
 * 
 * @author yallaiah.eswar
 *
 */
@Service
public class PostImpl implements PostIntf {

	@Autowired
	private PostRest postRest;

	@Autowired
	private UserRest userRest;
	
	/**
	 * Call class that will trigger rest call to get list of post by a specific user
	 * Method to activate sublist base on top value and returned data size
	 */
	@Override
	public List<Post> getPosts(int userId, int top) {
		// TODO Auto-generated method stub
		List<Post> posts = postRest.getPostsByUserId(userId);
		if(posts.size() > top && top > 0) {
			posts = posts.subList(0, top);
		}
		return posts;
	}
	
	/**
	 * Call class that will trigger rest call to create a new post by a specific user
	 * Call class that will trigger rest call to retrieve post creator user details
	 */
	@Override
	public User addPost(int userId, PostInput postInput) {
		// TODO Auto-generated method stub
		Post post = postRest.addPost(userId, postInput);
		//To add failure handler later
		if(post == null) {
			return null;
		}
		return userRest.getUserById(userId);
	}

	/**
	 * Call class that will trigger rest call to update a post by a specific user
	 * Call class that will trigger rest call to retrieve post updater user details
	 */
	@Override
	public User updatePost(int userId, int postId, PostInput postInput) {
		// TODO Auto-generated method stub
		String post = postRest.updatePost(userId, postId, postInput);
		//To add failure handler later
		if(post == null) {
			return null;
		}
		return userRest.getUserById(userId);
	}
	
	/**
	 * Call class that will trigger rest call to delete a specific post by a specific user
	 * Call class that will trigger rest call to retrieve post deleter user details
	 */
	@Override
	public User deletePost(int userId, int postId) {
		// TODO Auto-generated method stub
		String post = postRest.deletePost(userId, postId);
		//To add failure handler later
		if(post == null) {
			return null;
		}
		return userRest.getUserById(userId);
	}


}
