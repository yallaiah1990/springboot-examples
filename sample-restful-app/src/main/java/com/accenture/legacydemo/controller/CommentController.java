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

import com.accenture.legacydemo.model.Comment;
import com.accenture.legacydemo.service.intf.CommentIntf;

/**
 * 
 * @author yallaiah.eswar
 *
 */
@RestController
public class CommentController {
	@Autowired
	private CommentIntf commentService;

	@GetMapping("/posts/{postId}/comments")
	public List<Comment> getPostComments(@PathVariable("postId") Integer postId) {
		return commentService.getPostComments(postId);
	}

	@PostMapping("/posts/{postId}/comments")
	public Comment addComment(@PathVariable("postId") Integer postId, @RequestBody Comment comment) {
		return commentService.addComment(postId, comment);
	}

	@DeleteMapping("posts/{postId}/comments/{commentId}")
	public String deleteComment(@PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId) {
		return commentService.deleteComment(postId, commentId);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public String updateComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId,
			@PathVariable("commentId") Integer commentId) {
		return commentService.updateComment(comment, postId, commentId);
	}
}
