package com.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.GroupPage;
import com.demo.graphql.model.GroupPageMember;
import com.demo.graphql.model.GroupPagePost;
import com.demo.graphql.service.intf.GroupPageMemberIntf;
import com.demo.graphql.service.intf.GroupPagePostIntf;

/**
 * Resolver from type GroupPage. Class naming convention [TypeName]+Resolver Methods
 * and its corresponding return type were based from the type GroupPage. Method
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
public class GroupPageResolver implements GraphQLResolver<GroupPage> {

	@Autowired
	private GroupPagePostIntf groupPagePostService;
	
	@Autowired
	private GroupPageMemberIntf groupPageMemberService;
	
	public List<GroupPagePost> getPosts(GroupPage group, int top) {
		List<GroupPagePost> groupPagePosts = null;
		try{
			groupPagePosts = groupPagePostService.getGroupPagePosts(group.getId(), top);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in getting group page post list",e.toString());
		}
		return groupPagePosts;
	}
	
	public List<GroupPageMember> getMembers(GroupPage group, int top) {
		List<GroupPageMember> groupPageMembers = null;
		try {
			groupPageMembers = groupPageMemberService.getGroupPageMembers(group.getId(), top);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in getting group page member list",e.toString());
		}
		return groupPageMembers;
	}
}
