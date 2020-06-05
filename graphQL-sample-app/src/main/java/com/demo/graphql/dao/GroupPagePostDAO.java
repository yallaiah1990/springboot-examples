package com.demo.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.demo.graphql.model.GroupPage;
import com.demo.graphql.model.GroupPagePost;
import com.demo.graphql.repository.GroupPagePostRepository;
import com.demo.graphql.repository.GroupPageRepository;

@Component
public class GroupPagePostDAO {

	@Autowired
	private GroupPageRepository groupPageRepository;
	
	@Autowired
	private GroupPagePostRepository groupPagePostRepository;
	
	/**
	 * Add a Post to a specific Group Page
	 * Validate if Group Page is existing before adding post
	 * 
	 * @param groupPageId
	 * @param groupPost
	 * @return
	 */
	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePost groupPost) {
		// TODO Auto-generated method stub
		GroupPagePost groupPagePost = null;
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if(groupPageFound.isPresent()) {
			groupPagePost = groupPagePostRepository.save(groupPost);
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return groupPagePost;
	}

	/**
	 * Update a Post to a specific Group Page
	 * Validate if Group Page and Post is existing before updating post
	 * 
	 * @param groupPageId
	 * @param groupPagePostId
	 * @param groupPagePost
	 * @return
	 */
	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePost groupPagePost) {
		// TODO Auto-generated method stub
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if(groupPageFound.isPresent()) {
			Optional<GroupPagePost> groupPagePostFound = groupPagePostRepository.findById(groupPagePostId);
			if(groupPagePostFound.isPresent()) {
				groupPagePost = groupPagePostRepository.save(groupPagePost);
			}
			else {
				//To be updated for GraphQL Error Handling
				throw new IllegalArgumentException("Group Page Post for update not found!!!");
			}
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return groupPagePost;
	}

	/**
	 * Delete a Post to a specific Group Page
	 * Validate if Group Page and Post is existing before deleting post
	 * 
	 * @param groupPageId
	 * @param groupPagePostId
	 * @return
	 */
	public String deleteGroupPagePost(int groupPageId, int groupPagePostId) {
		// TODO Auto-generated method stub
		String message = null;
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if(groupPageFound.isPresent()) {
			Optional<GroupPagePost> groupPagePostFound = groupPagePostRepository.findById(groupPagePostId);
			if(groupPagePostFound.isPresent()) {
				groupPagePostRepository.deleteById(groupPagePostId);
				message = "Successfully deleted group page post with id: " + groupPagePostId;
			}
			else {
				//To be updated for GraphQL Error Handling
				throw new IllegalArgumentException("Group Page Post for update not found!!!");
			}
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return message;
	}

	/**
	 * Query list of Post of a specific Group Page
	 * Validate if Group Page is existing before getting post list
	 * 
	 * @param groupPageId
	 * @param top
	 * @return
	 */
	public List<GroupPagePost> getGroupPagePosts(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPagePost> groupPagePosts = null;
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if(groupPageFound.isPresent()) {
			Pageable firstPageWithTopElements = PageRequest.of(0, top);
			groupPagePosts = groupPagePostRepository.findByGroupPageId(groupPageId, firstPageWithTopElements);
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return groupPagePosts;
	}
	
	/**
	 * Get Post list count of a specific Group Page
	 * Validate if Group Page is existing before getting post count
	 * 
	 * @param groupPageId
	 * @return
	 */
	public int getCountByGroupPageId(int groupPageId) {
		int count = 0;
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if(groupPageFound.isPresent()) {
			count = groupPagePostRepository.countByGroupPageId(groupPageId);
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return count;
	}
}
