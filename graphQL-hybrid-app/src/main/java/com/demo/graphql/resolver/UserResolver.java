package com.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.Friend;
import com.demo.graphql.model.Post;
import com.demo.graphql.model.User;
import com.demo.graphql.service.intf.FriendIntf;
import com.demo.graphql.service.intf.PostIntf;

/**
 * Resolver from type User Class naming convention [TypeName]+Resolver Methods
 * and its corresponding return type were based from the type User. Method
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
public class UserResolver implements GraphQLResolver<User> {

	@Autowired
	private FriendIntf friendService;

	@Autowired
	private PostIntf postService;

	public List<Friend> getFriends(User user, int top) {
		List<Friend> friendList = null;
		try {
			friendList = friendService.getFriends(user.getId(), top);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting friend list", e.toString());
		}
		return friendList;
	}

	public List<Post> getPosts(User user, int top) {
		List<Post> postList = null;
		try {
			postList = postService.getPosts(user.getId(), top);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting post list", e.toString());
		}
		return postList;
	}

}
