package com.accenture.legacydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.legacydemo.model.Post;
import com.accenture.legacydemo.service.intf.PostIntf;

/**
 * 
 * @author yallaiah.eswar
 *
 */
@RestController
public class PostController {

	@Autowired
	private PostIntf postService;

	@GetMapping("/profile/{userId}/posts")
	public List<Post> getPostsByUserId(@PathVariable("userId") Integer userId) {
		return postService.getPostsByUserId(userId);
	}

	@GetMapping("/posts/{postId}")
	public Post getPostById(@PathVariable("postId") Integer postId) {
		return postService.getPostById(postId);
	}

	@PostMapping("/profile/{userId}/posts")
	public Post addPost(@PathVariable("userId") Integer userId, @RequestBody Post post) {
		return postService.addPost(userId, post);
	}

	@DeleteMapping("/profile/{userId}/posts/{postId}")
	public String deletePost(@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {
		return postService.deletePost(userId, postId);
	}

	@PutMapping("/profile/{userId}/posts/{postId}")
	public String updatePost(@RequestBody Post post, @PathVariable("userId") Integer userId,
			@PathVariable("postId") Integer postId) {
		return postService.updatePost(post, userId, postId);
	}
}
