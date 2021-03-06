package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.dao.GroupPageDAO;
import com.accenture.demo.graphql.dao.GroupPagePostDAO;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPagePost;
import com.accenture.demo.graphql.model.GroupPagePostInput;
import com.accenture.demo.graphql.service.intf.GroupPagePostIntf;

@Service
public class GroupPagePostImpl implements GroupPagePostIntf {

	@Autowired
	private GroupPagePostDAO groupPagePostDAO;

	@Autowired
	private GroupPageDAO groupPageDAO;
	
	/**
	 * Call class that will trigger db insert to add new GroupPagePost
	 * Throws Error for a null groupPagePost return null
	 */
	@Override
	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePostInput groupPost) {
		// TODO Auto-generated method stub
		GroupPagePost groupPagePostNew = new GroupPagePost();
		GroupPage groupPage = new GroupPage();
		groupPage.setId(groupPageId);
		groupPagePostNew.setContent(groupPost.getContent());
		groupPagePostNew.setGroupPage(groupPage);
		groupPagePostNew = groupPagePostDAO.addGroupPagePost(groupPageId, groupPagePostNew);
		if(groupPagePostNew == null) {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Add Group Post returned null!!!");
		}
		return groupPagePostNew;
	}

	/**
	 * Call class that will trigger db update of existing GroupPagePost
	 * Throws Error for a null groupPagePost return null
	 */
	@Override
	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePostInput groupPost) {
		// TODO Auto-generated method stub
		GroupPagePost groupPagePostUpdate = new GroupPagePost();
		GroupPage groupPage = new GroupPage();
		groupPage.setId(groupPageId);
		groupPagePostUpdate.setId(groupPagePostId);
		groupPagePostUpdate.setContent(groupPost.getContent());
		groupPagePostUpdate.setGroupPage(groupPage);
		groupPagePostUpdate = groupPagePostDAO.addGroupPagePost(groupPageId, groupPagePostUpdate);
		if(groupPagePostUpdate == null) {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Update Group Post returned null!!!");
		}
		return groupPagePostUpdate;
	}

	/**
	 * Call class that will trigger db delete of existing GroupPagePost
	 * Throws Error for a null message return null
	 */
	@Override
	public GroupPage deleteGroupPagePost(int groupPageId, int groupPagePostId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		String message = groupPagePostDAO.deleteGroupPagePost(groupPageId, groupPagePostId);
		if(message != null) {
			groupPage = groupPageDAO.getGroupPageById(groupPageId);
		}
		else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Delete Group Post returned null!!!");
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db query to get list of GroupPagePost by groupPageId
	 * Class will validate top input. Set top to current GroupPagePost count if less than or equal to 0
	 */
	@Override
	public List<GroupPagePost> getGroupPagePosts(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPagePost> groupPagePosts = null;
		int topCount = top;
		if(top < 1) {
			topCount = groupPagePostDAO.getCountByGroupPageId(groupPageId);
		}
		
		if(topCount > 1) {
			groupPagePosts = groupPagePostDAO.getGroupPagePosts(groupPageId, topCount);
		}
		return groupPagePosts;
	}

}
