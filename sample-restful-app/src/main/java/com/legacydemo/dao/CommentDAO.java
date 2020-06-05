package com.legacydemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legacydemo.model.Comment;
import com.legacydemo.model.Post;
import com.legacydemo.repository.CommentRepository;
import com.legacydemo.repository.PostRepository;

@Component
public class CommentDAO {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getPostComments(Integer postId) {
		List<Comment> comments = null;
		Optional<Post> postFound = postRepository.findById(postId);
		if (postFound.isPresent()) {
			comments = commentRepository.findByPostId(postId);
		} else {
			throw new IllegalArgumentException("Post not found!!!");
		}

		return comments;
	}

	public Comment addComment(Integer postId, Comment comment) {
		Optional<Post> postFound = postRepository.findById(postId);
		if (postFound.isPresent()) {
			Post post = new Post();
			post.setId(postId);
			comment.setPost(post);
			commentRepository.save(comment);
		} else {
			throw new IllegalArgumentException("Post for comment creation not found!!!");
		}
		return comment;
	}

	public String deleteComment(Integer postId, Integer commentId) {
		String responseMessage = null;
		Optional<Comment> commentFound = commentRepository.findById(commentId);
		if (commentFound.isPresent()) {
			Comment comment = commentRepository.getOne(commentId);
			if (comment.getPost().getId() == postId) {
				commentRepository.deleteById(commentId);
				responseMessage = "Successfully deleted comment with id: " + comment.getId();
			} else {
				throw new IllegalArgumentException("Comment does not belong to the post!");
			}
		} else {
			throw new IllegalArgumentException("Comment for deletion not found!");
		}
		return responseMessage;
	}

	public String updateComment(Comment comment, Integer postId, Integer commentId) {
		String responseMessage = null;
		Optional<Comment> commentFound = commentRepository.findById(commentId);
		if (commentFound.isPresent()) {
			Comment commentTemp = commentRepository.getOne(commentId);
			if (commentTemp.getPost().getId() == postId) {
				comment.setPost(commentTemp.getPost());
				commentRepository.save(comment);
				responseMessage = "Successfully updated comment with id: " + comment.getId();
			} else {
				throw new IllegalArgumentException("Comment does not belong to the post!");
			}
		} else {
			throw new IllegalArgumentException("Comment for update not found!");
		}
		return responseMessage;
	}

}
