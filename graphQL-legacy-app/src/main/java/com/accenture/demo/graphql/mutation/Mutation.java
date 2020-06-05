package com.accenture.demo.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.CommentIntf;
import com.accenture.demo.graphql.service.intf.PostIntf;
import com.accenture.demo.graphql.service.intf.UserIntf;
import com.accenture.demo.graphql.model.Address;
import com.accenture.demo.graphql.model.CommentInput;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.model.PostInput;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

/**
 * Root resolver from type Mutation
 * Class naming convention will simply be Mutation based on schema
 * Methods and its corresponding return type were based from the elements of the graphql schema
 * Method naming convention ElementName.FirstCharacterToLower] for java standard purpose.
 * Method to call service classes to create/update/delete.
 * 
 * @author yallaiah.eswar
 *
 */
@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private UserIntf userService;

	@Autowired
	private PostIntf postService;

	@Autowired
	private CommentIntf commentService;

	public User addUser(String name, Address address) {
		return userService.addUser(name, address);
	}

	public String deleteUser(int userId) {
		return userService.deleteUser(userId);
	}

	public User addPost(int userId, PostInput postInput) {
		return postService.addPost(userId, postInput);
	}

	public User updatePost(int userId, int postId, PostInput postInput) {
		return postService.updatePost(userId, postId, postInput);
	}

	public User deletePost(int userId, int postId) {
		return postService.deletePost(userId, postId);
	}

	public Post addComment(int postId, CommentInput commentInput) {
		return commentService.addComment(postId, commentInput);
	}

	public Post updateComment(int postId, int commentId, CommentInput commentInput) {
		return commentService.updateComment(postId, commentId, commentInput);
	}

	public Post deleteComment(int postId, int commentId) {
		return commentService.deleteComment(postId, commentId);
	}
}
