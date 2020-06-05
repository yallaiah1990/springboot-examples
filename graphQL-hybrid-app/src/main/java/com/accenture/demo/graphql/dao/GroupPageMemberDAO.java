package com.accenture.demo.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPageMember;
import com.accenture.demo.graphql.repository.GroupPageMemberRepository;
import com.accenture.demo.graphql.repository.GroupPageRepository;

@Component
public class GroupPageMemberDAO {

	@Autowired
	private GroupPageMemberRepository groupPageMemberRepository;
	
	@Autowired
	private GroupPageRepository groupPageRepository;
	
	/**
	 * Add a Member to a specific Group Page
	 * Validate if Group Page is existing before adding member and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param userId
	 * @return
	 */
	public GroupPageMember addGroupPageMember(int groupPageId, int userId) {
		// TODO Auto-generated method stub
		GroupPageMember groupPageMember = new GroupPageMember();
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				GroupPage groupPage = new GroupPage();
				groupPage.setId(groupPageId);
				groupPageMember.setUserId(userId);
				groupPageMember.setGroupPage(groupPage);
				groupPageMember = groupPageMemberRepository.save(groupPageMember);
			}
			else {
				//To be updated for GraphQL Error Handling
				throw new GraphQLException("Error Inserting in Group Page Member DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Inserting in Group Page Member DB",e.toString());
		}
		return groupPageMember;
	}

	/**
	 * Delete a Member to a specific Group Page
	 * Validate if Group Page and Member is existing before deleting member and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param groupPageMemberId
	 * @return
	 */
	public String deleteGroupPageMember(int groupPageId, int groupPageMemberId) {
		// TODO Auto-generated method stub
		String message = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				Optional<GroupPageMember> groupPageMemberFound = groupPageMemberRepository.findById(groupPageMemberId);
				if(groupPageMemberFound.isPresent()) {
					groupPageMemberRepository.deleteById(groupPageMemberId);
					message = "Successfully deleted group page member with id: " + groupPageMemberId;
				}
				else {
					throw new GraphQLException("Error Deleting in Group Page Member DB","Group Page Member not found!!!");
				}
			}
			else {
				throw new GraphQLException("Error Deleting in Group Page Member DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Deleting in Group Page Member DB",e.toString());
		}
		
		return message;
	}

	/**
	 * Query list of Member of a specific Group Page
	 * Validate if Group Page is existing before getting member list and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param top
	 * @return
	 */

	public List<GroupPageMember> getGroupPageMembers(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPageMember> groupPageMembers = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				Pageable firstPageWithTopElements = PageRequest.of(0, top);
				groupPageMembers = groupPageMemberRepository.findByGroupPageId(groupPageId, firstPageWithTopElements);
			}
			else {
				throw new GraphQLException("Error Querying in Group Page Member DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page Member DB",e.toString());
		}
		return groupPageMembers;
	}
	
	/**
	 * Get Member list count of a specific Group Page
	 * Validate if Group Page is existing before getting member count and throw GraphQLException
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
				count = groupPageMemberRepository.countByGroupPageId(groupPageId);}
			else {
				throw new GraphQLException("Error Querying in Group Page Member DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page Member DB",e.toString());
		}

		return 	count;
	}
	
	/**
	 * Delete group member by userId
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public String deleteGroupPageMemberByUserId(int userId) {
		String message = null;
		try {
			groupPageMemberRepository.deleteByUserId(userId);
			message = "Executed delete group page member by userId completed: " + userId;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Deleting in Group Page Member DB",e.toString());
		}
		return message;
	}
}
