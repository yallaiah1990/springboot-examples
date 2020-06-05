package com.demo.graphql.service.intf;

import java.util.List;

import com.demo.graphql.model.Comment;
import com.demo.graphql.model.CommentInput;
import com.demo.graphql.model.Post;

public interface CommentIntf {
	public List<Comment> getComments(int postId, int top);

	public Post addComment(int postId, CommentInput commentInput);

	public Post updateComment(int postId, int commentId, CommentInput commentInput);
	
	public Post deleteComment(int postId, int commentId);
}
