package com.accenture.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.exception.GraphQLException;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.GroupPageIntf;
import com.accenture.demo.graphql.service.intf.UserIntf;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

/**
 * Root resolver from type Query Class naming convention [TypeName]+Resolver
 * Methods and its corresponding return type were based from the elements of the
 * graphql schema Method naming convention
 * get+[ElementName.FirstCharacterToUpper] Method to call service classes to
 * fetch return data.
 * Error in Query will capture only for GraphQLException from Service Class and Unknown Exception
 * 
 * 
 * @author atcp ies ta
 *
 */
@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private UserIntf userService;
	
	@Autowired
	private GroupPageIntf groupPageService;

	public List<User> getAllUsers() {
		List<User> userList = null;
		try {
			userList = userService.getAllUsers();
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting user list", e.toString());
		}
		return userList;
	}

	public User getUserById(int userId) {
		User user = null;
		try {
			user = userService.getUserById(userId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting user details", e.toString());
		}
		return user;
	}

	public List<GroupPage> getAllGroupPages() {
		List<GroupPage> groupPages = null;
		try {
			groupPages = groupPageService.getAllGroupPages();
		}		
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in getting group page list",e.toString());
		}
		return groupPages;
	}

	public GroupPage getGroupPageById(int groupPageId) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPageService.getGroupPageById(groupPageId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in getting group page details",e.toString());
		}
		return groupPage;
	}
}
