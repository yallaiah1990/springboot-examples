package com.accenture.legacydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.legacydemo.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByUserId(int userId);
}
