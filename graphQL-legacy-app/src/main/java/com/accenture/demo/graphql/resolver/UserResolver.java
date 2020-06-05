package com.accenture.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.model.Friend;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.model.User;
import com.accenture.demo.graphql.service.intf.FriendIntf;
import com.accenture.demo.graphql.service.intf.PostIntf;
import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Resolver from type User
 * Class naming convention [TypeName]+Resolver
 * Methods and its corresponding return type were based from the type User.
 * Method naming convention get+[ElementName.FirstCharacterToUpper].
 * Method input differs from root resolver which takes literally what is in the schema.
 * Inputs for non root/query resolver includes Resolver Object + graphql schema input 
 * Method to call service classes to fetch return data.
 * 
 * @author yallaiah.eswar
 *
 */
@Component
public class UserResolver implements GraphQLResolver<User> {

	@Autowired
	private FriendIntf friendService;

	@Autowired
	private PostIntf postService;
	
	public List<Friend> getFriends(User user, int top) {
		return friendService.getFriends(user.getId(), top);
	}

	public List<Post> getPosts(User user, int top) {
		return postService.getPosts(user.getId(), top);
	}

}
