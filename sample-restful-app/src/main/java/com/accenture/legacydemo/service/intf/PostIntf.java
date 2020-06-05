package com.accenture.legacydemo.service.intf;

import java.util.List;

import com.accenture.legacydemo.model.Post;

public interface PostIntf {
	public List<Post> getPostsByUserId(Integer userId);

	public Post getPostById(Integer postId);

	public Post addPost(Integer id, Post post);

	public String deletePost(Integer userId, Integer postId);

	public String updatePost(Post post, Integer userId, Integer postId);
}
