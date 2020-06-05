package com.accenture.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.demo.graphql.dao.GroupPageDAO;
import com.accenture.demo.graphql.dao.GroupPageMemberDAO;
import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.legacy.rest.UserRest;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPageMember;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.GroupPageMemberIntf;

@Service
public class GroupPageMemberImpl implements GroupPageMemberIntf {

	@Autowired
	private GroupPageMemberDAO groupPageMemberDAO;

	@Autowired
	private GroupPageDAO groupPageDAO;
	
	@Autowired
	private UserRest userRest;

	/**
	 * Call class that will trigger db insert to add new GroupPageMember
	 * Throws GraphQLException for a null user details and/or groupPageMember return
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public GroupPage addGroupPageMember(int groupPageId, int userId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			User user = userRest.getUserById(userId);
			if(user != null) {
				GroupPageMember groupPageMember = groupPageMemberDAO.addGroupPageMember(groupPageId, userId);
				if (groupPageMember != null) {
					groupPage = groupPageDAO.getGroupPageById(groupPageId);
				} else {
					throw new GraphQLException("Error in group page service processing","Add Group Member returned null!!!");
				}
			}
			else {
				throw new GraphQLException("Error in group page service processing","User details returned null!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page member service processing",e.toString());
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db delete of existing GroupPageMember
	 * Throws GraphQLException Error for a null message return
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public GroupPage deleteGroupPageMember(int groupPageId, int groupPageMemberId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			String message = groupPageMemberDAO.deleteGroupPageMember(groupPageId, groupPageMemberId);
			if (message != null) {
				groupPage = groupPageDAO.getGroupPageById(groupPageId);
			} else {
				//To be updated for GraphQL Error Handling
				throw new GraphQLException("Error in group page member service processing","Delete Group Member returned null!!!");
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page member service processing",e.toString());
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db query to get list of GroupPageMember by groupPageId
	 * Class will validate top input. Set top to current GroupPageMember count if less than or equal to 0
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<GroupPageMember> getGroupPageMembers(int groupPageId, int top) {
		// TODO Auto-generated method stub
		List<GroupPageMember> groupPageMembers = null;
		try  {
			int topCount = top;
			if (top < 1) {
				topCount = groupPageMemberDAO.getCountByGroupPageId(groupPageId);
			}
			
			if(topCount > 0) {
				groupPageMembers = groupPageMemberDAO.getGroupPageMembers(groupPageId, topCount);
			}
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page member service processing",e.toString());
		}		
		return groupPageMembers;
	}

}
