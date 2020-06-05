package com.accenture.demo.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.model.About;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.repository.GroupPageRepository;

@Component
public class GroupPageDAO {

	@Autowired
	private GroupPageRepository groupPageRepository;

	/**
	 * Get All Group Pages
	 * 
	 * @return
	 */
	public List<GroupPage> getAllGroupPages() {
		// TODO Auto-generated method stub
		return groupPageRepository.findAll();
	}

	/**
	 * Get Group Pages by Id
	 * Validate group page if existing before returning group page
	 * 
	 * @param groupPageId
	 * @return
	 */
	public GroupPage getGroupPageById(int groupPageId) {
		// TODO Auto-generated method stub
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		GroupPage groupPage = null;
		if (groupPageFound.isPresent()) {
			groupPage = groupPageFound.get();
		} else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group not found!!!");
		}
		return groupPage;
	}

	/**
	 * Add new Group Page
	 * 
	 * @param name
	 * @param about
	 * @return
	 */
	public GroupPage addGroupPage(String name, About about) {
		// TODO Auto-generated method stub
		GroupPage groupPage = new GroupPage();
		groupPage.setName(name);
		groupPage.setAbout(about);
		groupPageRepository.save(groupPage);
		return groupPage;
	}

	/**
	 * Update existing Group Page.
	 * Validate group page if existing before updating group page
	 * 
	 * @param groupPageId
	 * @param name
	 * @param about
	 * @return
	 */
	public GroupPage updateGroupPage(int groupPageId, String name, About about) {
		// TODO Auto-generated method stub
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		GroupPage groupPage = null;
		if (groupPageFound.isPresent()) {
			groupPage = groupPageFound.get();
			groupPage.setName(name);
			about.setId(groupPage.getAbout().getId());
			groupPage.setAbout(about);
			groupPageRepository.save(groupPage);
		} else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group not found!!!");
		}
		return groupPage;
	}

	/**
	 * Delete existing Group Page.
	 * Validate if Group Page is existing before deleting group page.
	 * 
	 * @param groupPageId
	 * @return
	 */
	public String deleteGroupPage(int groupPageId) {
		// TODO Auto-generated method stub
		String message = null;
		Optional<GroupPage> groupPageFound = groupPageRepository.findById(groupPageId);
		if (groupPageFound.isPresent()) {
			groupPageRepository.deleteById(groupPageId);
			message = "Successfully deleted group page with id: " + groupPageId;
		} else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Group Page not found!!!");
		}
		return message;
	}
}
