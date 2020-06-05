package com.legacydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	public List<Comment> findByPostId(int postId);
}
