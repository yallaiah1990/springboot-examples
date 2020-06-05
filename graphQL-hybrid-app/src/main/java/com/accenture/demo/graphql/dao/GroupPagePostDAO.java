package com.accenture.demo.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPagePost;
import com.accenture.demo.graphql.repository.GroupPagePostRepository;
import com.accenture.demo.graphql.repository.GroupPageRepository;
@Component
public class GroupPagePostDAO {

	@Autowired
	private GroupPageRepository groupPageRepository;
	
	@Autowired
	private GroupPagePostRepository groupPagePostRepository;
	
	/**
	 * Add a Post to a specific Group Page
	 * Validate if Group Page is existing before adding post and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param groupPost
	 * @return
	 */
	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePost groupPost) {
		// TODO Auto-generated method stub
		GroupPagePost groupPagePost = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				groupPagePost = groupPagePostRepository.save(groupPost);
			}
			else {
				throw new GraphQLException("Error Inserting in Group Page Post DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Inserting in Group Page Post DB",e.toString());
		}
		return groupPagePost;
	}

	/**
	 * Update a Post to a specific Group Page
	 * Validate if Group Page and Post is existing before updating post and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param groupPagePostId
	 * @param groupPagePost
	 * @return
	 */
	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePost groupPagePost) {
		// TODO Auto-generated method stub
		GroupPagePost groupPagePostUpdated = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				Optional<GroupPagePost> groupPagePostFound = groupPagePostRepository.findById(groupPagePostId);
				if(groupPagePostFound.isPresent()) {
					groupPagePostUpdated = groupPagePostRepository.save(groupPagePost);
				}
				else {
					throw new GraphQLException("Error Updating in Group Page Post DB","Group Page Post for update not found!!!");
				}
			}
			else {
				throw new GraphQLException("Error Updating in Group Page Post DB","Group Page not found!!!");
			}
		}		
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Updating in Group Page Post DB",e.toString());
		}

		return groupPagePostUpdated;
	}

	/**
	 * Delete a Post to a specific Group Page
	 * Validate if Group Page and Post is existing before deleting post and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param groupPagePostId
	 * @return
	 */
	public String deleteGroupPagePost(int groupPageId, int groupPagePostId) {
		// TODO Auto-generated method stub
		String message = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				Optional<GroupPagePost> groupPagePostFound = groupPagePostRepository.findById(groupPagePostId);
				if(groupPagePostFound.isPresent()) {
					groupPagePostRepository.deleteById(groupPagePostId);
					message = "Successfully deleted group page post with id: " + groupPagePostId;
				}
				else {
					throw new GraphQLException("Error Deleting in Group Page Post DB","Group Page Post for update not found!!!");
				}
			}
			else {
				throw new GraphQLException("Error Deleting in Group Page Post DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Deleting in Group Page Post DB",e.toString());
		}
		
		return message;
	}

	/**
	 * Query list of Post of a specific Group Page
	 * Validate if Group Page is existing before getting post list and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param top
	 * @return
	 */
	public List<GroupPagePost> getGroupPagePosts(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPagePost> groupPagePosts = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				Pageable firstPageWithTopElements = PageRequest.of(0, top);
				groupPagePosts = groupPagePostRepository.findByGroupPageId(groupPageId, firstPageWithTopElements);
			}
			else {
				throw new GraphQLException("Error Querying in Group Page Post DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page Post DB",e.toString());
		}
		return groupPagePosts;
	}
	
	/**
	 * Get Post list count of a specific Group Page
	 * Validate if Group Page is existing before getting post count and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @return
	 */
	public int getCountByGroupPageId(int groupPageId) {
		int count = 0;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				count = groupPagePostRepository.countByGroupPageId(groupPageId);
			}
			else {
				throw new GraphQLException("Error Querying in Group Page Post DB","Group Page not found!!!");
			}	
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page Post DB",e.toString());
		}
		return count;
	}
}
