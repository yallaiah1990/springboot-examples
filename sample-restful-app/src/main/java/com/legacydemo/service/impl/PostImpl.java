package com.legacydemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legacydemo.dao.PostDAO;
import com.legacydemo.model.Post;
import com.legacydemo.service.intf.PostIntf;

@Service
public class PostImpl implements PostIntf {

	@Autowired
	private PostDAO postDAO;
	
	@Override
	public List<Post> getPostsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return postDAO.getPostsByUserId(userId);
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return postDAO.getPostById(postId);
	}

	@Override
	public Post addPost(Integer id, Post post) {
		// TODO Auto-generated method stub
		return postDAO.addPost(id, post);
	}

	@Override
	public String deletePost(Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		return postDAO.deletePost(userId, postId);
	}

	@Override
	public String updatePost(Post post, Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		return postDAO.updatePost(post, userId, postId);
	}

}
