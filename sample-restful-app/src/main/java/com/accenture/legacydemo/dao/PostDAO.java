package com.accenture.legacydemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.legacydemo.model.Post;
import com.accenture.legacydemo.model.User;
import com.accenture.legacydemo.repository.PostRepository;
import com.accenture.legacydemo.repository.UserRepository;

@Component
public class PostDAO {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Post> getPostsByUserId(Integer userId) {
		List<Post> posts = null;
		Optional<User> userFound = userRepository.findById(userId);
		if (userFound.isPresent()) {
			posts = postRepository.findByUserId(userId);
		} else {
			throw new IllegalArgumentException("User not found!!!");
		}
		return posts;
	}

	public Post getPostById(Integer postId) {
		Post post = null;
		Optional<Post> postFound = postRepository.findById(postId);
		if (postFound.isPresent()) {
			post = postFound.get();
		} else {
			throw new IllegalArgumentException("Post not found!!!");
		}
		return post;
	}

	public Post addPost(Integer id, Post post) {
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isPresent()) {
			User user = new User();
			user.setId(id);
			post.setUser(user);
			postRepository.save(post);
		} else {
			throw new IllegalArgumentException("User for post creation not found!!!");
		}
		return post;
	}

	public String deletePost(Integer userId, Integer postId) {
		String responseMessage = null;
		Optional<Post> postFound = postRepository.findById(postId);
		if (postFound.isPresent()) {
			Post post = postRepository.getOne(postId);
			if (post.getUser().getId() == userId) {
				postRepository.delete(post);
				responseMessage = "Successfully deleted post with id: " + post.getId();
			} else {
				throw new IllegalArgumentException("Post does not belong to the user!");
			}
		} else {
			throw new IllegalArgumentException("Post for deletion not found!");
		}
		return responseMessage;
	}

	public String updatePost(Post post, Integer userId, Integer postId) {
		String responseMessage = null;
		Optional<Post> postFound = postRepository.findById(postId);
		if (postFound.isPresent()) {
			Post postTemp = postRepository.getOne(postId);
			if (postTemp.getUser().getId() == userId) {
				post.setUser(postTemp.getUser());
				postRepository.save(post);
				responseMessage = "Successfully update post with id: " + post.getId();
			} else {
				throw new IllegalArgumentException("Post does not belong to the user!");
			}
		} else {
			throw new IllegalArgumentException("Post for update not found!");
		}
		return responseMessage;
	}
}
