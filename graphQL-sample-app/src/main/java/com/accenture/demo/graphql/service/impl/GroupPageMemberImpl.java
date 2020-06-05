package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.dao.GroupPageDAO;
import com.accenture.demo.graphql.dao.GroupPageMemberDAO;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPageMember;
import com.accenture.demo.graphql.service.intf.GroupPageMemberIntf;

@Service
public class GroupPageMemberImpl implements GroupPageMemberIntf {

	@Autowired
	private GroupPageMemberDAO groupPageMemberDAO;

	@Autowired
	private GroupPageDAO groupPageDAO;

	/**
	 * Call class that will trigger db insert to add new GroupPageMember
	 * Throws Error for a null groupPageMember return
	 */
	@Override
	public GroupPage addGroupPageMember(int groupPageId, int userId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		GroupPageMember groupPageMember = groupPageMemberDAO.addGroupPageMember(groupPageId, userId);
		if (groupPageMember != null) {
			groupPage = groupPageDAO.getGroupPageById(groupPageId);
		} else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Add Group Member returned null!!!");
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db delete of existing GroupPageMember
	 * Throws Error for a null message return
	 */
	@Override
	public GroupPage deleteGroupPageMember(int groupPageId, int groupPageMemberId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		String message = groupPageMemberDAO.deleteGroupPageMember(groupPageId, groupPageMemberId);
		if (message != null) {
			groupPage = groupPageDAO.getGroupPageById(groupPageId);
		} else {
			//To be updated for GraphQL Error Handling
			throw new IllegalArgumentException("Delete Group Member returned null!!!");
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db query to get list of GroupPageMember by groupPageId
	 * Class will validate top input. Set top to current GroupPageMember count if less than or equal to 0
	 */
	@Override
	public List<GroupPageMember> getGroupPageMembers(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPageMember> groupPageMembers = null;
		int topCount = top;
		if (top < 1) {
			topCount = groupPageMemberDAO.getCountByGroupPageId(groupPageId);
		}
		
		if(topCount > 0) {
			groupPageMembers = groupPageMemberDAO.getGroupPageMembers(groupPageId, topCount);
		}
		return groupPageMembers;
	}

}
