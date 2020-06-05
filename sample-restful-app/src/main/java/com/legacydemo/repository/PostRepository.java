package com.legacydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacydemo.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByUserId(int userId);
}
