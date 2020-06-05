package com.legacydemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legacydemo.dao.CommentDAO;
import com.legacydemo.model.Comment;
import com.legacydemo.service.intf.CommentIntf;

@Service
public class CommentImpl implements CommentIntf {

	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public List<Comment> getPostComments(Integer postId) {
		// TODO Auto-generated method stub
		return commentDAO.getPostComments(postId);
	}

	@Override
	public Comment addComment(Integer postId, Comment comment) {
		// TODO Auto-generated method stub
		return commentDAO.addComment(postId, comment);
	}

	@Override
	public String deleteComment(Integer postId, Integer commentId) {
		// TODO Auto-generated method stub
		return commentDAO.deleteComment(postId, commentId);
	}

	@Override
	public String updateComment(Comment comment, Integer postId, Integer commentId) {
		// TODO Auto-generated method stub
		return commentDAO.updateComment(comment, postId, commentId);
	}

}
