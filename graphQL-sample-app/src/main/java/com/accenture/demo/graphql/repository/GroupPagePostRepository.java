package com.accenture.demo.graphql.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.demo.graphql.model.GroupPagePost;

/**
 * JPA repository for GroupPagePost
 * Additional functions to fetch Group Page Post count and list using groupPageId
 * 
 * @author atcp ies ta
 *
 */
public interface GroupPagePostRepository extends JpaRepository<GroupPagePost, Integer> {
	public List<GroupPagePost> findByGroupPageId(int groupPageId, Pageable pageable);
	public int countByGroupPageId(int groupPageId);
}
