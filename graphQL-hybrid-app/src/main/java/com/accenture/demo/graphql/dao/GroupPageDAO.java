package com.accenture.demo.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.About;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.repository.GroupPageRepository;

@Component
public class GroupPageDAO {

	@Autowired
	private GroupPageRepository groupPageRepository;

	/**
	 * Get All Group Pages
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @return
	 */
	public List<GroupPage> getAllGroupPages() {
		// TODO Auto-generated method stub
		List<GroupPage> groupPages = null;
		try {
			groupPages =  groupPageRepository.findAll();
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page DB",e.toString());
		}
		return  groupPages;
	}

	/**
	 * Get Group Pages by Id
	 * Validate group page if existing before returning group page and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @return
	 */
	public GroupPage getGroupPageById(int groupPageId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if(groupPageFound.isPresent()) {
				groupPage = groupPageFound.get();
			}
			else {
				throw new GraphQLException("Error Querying in Group Page DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Querying in Group Page DB",e.toString());
		}
		return groupPage;
	}

	/**
	 * Add new Group Page
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param name
	 * @param about
	 * @return
	 */
	public GroupPage addGroupPage(String name, About about) {
		// TODO Auto-generated method stub
		GroupPage groupPage = new GroupPage();
		try {
			groupPage.setName(name);
			groupPage.setAbout(about);
			groupPageRepository.save(groupPage);
		}
		catch(Exception e) {
			throw new GraphQLException("Error Inserting in Group Page DB",e.toString());
		}
		return groupPage;
	}

	/**
	 * Update existing Group Page.
	 * Validate group page if existing before updating group page and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @param name
	 * @param about
	 * @return
	 */
	public GroupPage updateGroupPage(int groupPageId, String name, About about){
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if (groupPageFound.isPresent()) {
				groupPage = groupPageFound.get();
				groupPage.setName(name);
				about.setId(groupPage.getAbout().getId());
				groupPage.setAbout(about);
				groupPageRepository.save(groupPage);
			} else {
				throw new GraphQLException("Error Updating in Group Page DB","Group not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Updating in Group Page DB",e.toString());
		}
		return groupPage;
	}

	/**
	 * Delete existing Group Page.
	 * Validate if Group Page is existing before deleting group page and throw GraphQLException
	 * Capture any exception from doing a DB transaction throw GraphQLException
	 * 
	 * @param groupPageId
	 * @return
	 */
	public String deleteGroupPage(int groupPageId) {
		// TODO Auto-generated method stub
		String message = null;
		try {
			Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
			if (groupPageFound.isPresent()) {
				groupPageRepository.deleteById(groupPageId);
				message = "Successfully deleted group page with id: " + groupPageId;
			} else {
				throw new GraphQLException("Error Deleting Group Page DB","Group Page not found!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error Deleting in Group Page DB",e.toString());
		}

		return message;
	}
}
