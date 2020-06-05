package com.demo.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.GroupPageMember;
import com.demo.graphql.model.User;
import com.demo.graphql.service.intf.UserIntf;

/**
 * Resolver from type GroupPageMember. Class naming convention [TypeName]+Resolver Methods
 * and its corresponding return type were based from the type GroupPageMember. Method
 * naming convention get+[ElementName.FirstCharacterToUpper]. Method input
 * differs from root resolver which takes literally what is in the schema.
 * Inputs for non root/query resolver includes Resolver Object + graphql schema
 * input Method to call service classes to fetch return data.
 * Error in Query will capture only for GraphQLException from Service Class and Unknown Exception
 * 
 * @author atcp ies ta
 *
 */
@Component
public class GroupPageMemberResolver implements GraphQLResolver<GroupPageMember> {
	
	@Autowired
	private UserIntf userService;
	
	public User getUser(GroupPageMember groupPageMember) {
		User user = null;
		try {
			user = userService.getUserById(groupPageMember.getUserId());
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting user details", e.toString());
		}
		return user;
	}

}
