package com.demo.graphql.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.graphql.model.GroupPageMember;

/**
 * JPA repository for GroupPageMembers
 * Additional functions to fetch Group Page Member count and list using groupPageId
 * 
 * @author atcp ies ta
 *
 */
public interface GroupPageMemberRepository extends JpaRepository<GroupPageMember, Integer> {

	public List<GroupPageMember> findByGroupPageId(int groupPageId, Pageable pageable);
	public int countByGroupPageId(int groupPageId);
}
