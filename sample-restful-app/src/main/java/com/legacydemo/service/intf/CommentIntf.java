package com.legacydemo.service.intf;

import java.util.List;

import com.legacydemo.model.Comment;

public interface CommentIntf {
	public List<Comment> getPostComments(Integer postId);

	public Comment addComment(Integer postId, Comment comment);

	public String deleteComment(Integer postId, Integer commentId);

	public String updateComment(Comment comment, Integer postId, Integer commentId);
}
