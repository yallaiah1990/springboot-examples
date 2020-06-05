package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.dao.GroupPageMemberDAO;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.legacy.rest.UserRest;
import com.demo.graphql.model.Address;
import com.demo.graphql.model.User;
import com.demo.graphql.service.intf.UserIntf;

@Service
public class UserImpl implements UserIntf {

	@Autowired
	private UserRest userRest;
	
	@Autowired
	private GroupPageMemberDAO groupPageMemberDAO;

	/**
	 * Call class that will trigger rest call to get list of users
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = null;
		try {
			users = userRest.getAllUsers();
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in user service processing", e.toString());
		}
		return users;
	}

	/**
	 * Call class that will trigger rest call to get specific user details
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userRest.getUserById(userId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in user service processing", e.toString());
		}
		return user;
	}

	/**
	 * Call class that will trigger creation of new user
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public User addUser(String name, Address address) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userRest.addUser(name, address);
			if(user == null) {
				throw new GraphQLException("Error in user service processing", "Add of user returned null");
			}
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in user service processing", e.toString());
		}
		return user;
	}

	/**
	 * Call class that will trigger rest call to delete existing user
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 * Method to throw GraphQLException for business case like null response.
	 */
	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		String deleteUserMessage = null;
		try {
			String groupPageMemberMessage = groupPageMemberDAO.deleteGroupPageMemberByUserId(userId);
			if(groupPageMemberMessage != null) {
				deleteUserMessage = userRest.deleteUser(userId);
				if(deleteUserMessage == null) {
					throw new GraphQLException("Error in user service processing", "Delete of user returned null");
				}
			}
			else {
				throw new GraphQLException("Error in user service processing", "Delete of group member returned null");
			}

		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Error in user service processing", e.toString());
		}
		return deleteUserMessage;
	}

}
