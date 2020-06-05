package com.demo.graphql.service.intf;

import java.util.List;

import com.demo.graphql.model.Post;
import com.demo.graphql.model.PostInput;
import com.demo.graphql.model.User;

public interface PostIntf {
	public List<Post> getPosts(int userId, int top);

	public User addPost(int userId, PostInput postInput);

	public User updatePost(int userId, int postId, PostInput postInput);
	
	public User deletePost(int userId, int postId);
}
