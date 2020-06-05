package com.accenture.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.UserIntf;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

/**
 * Root resolver from type Query
 * Class naming convention [TypeName]+Resolver
 * Methods and its corresponding return type were based from the elements of the graphql schema
 * Method naming convention get+[ElementName.FirstCharacterToUpper]
 * Method to call service classes to fetch return data.
 * 
 * @author yallaiah.eswar
 *
 */
@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private UserIntf userService;
	
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	public User getUserById(int userId) {
		return userService.getUserById(userId);
	}
}
