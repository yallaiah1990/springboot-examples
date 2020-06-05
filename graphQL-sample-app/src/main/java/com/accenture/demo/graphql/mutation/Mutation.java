package com.accenture.demo.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.demo.graphql.model.AboutInput;
import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPagePost;
import com.accenture.demo.graphql.model.GroupPagePostInput;
import com.accenture.demo.graphql.service.intf.GroupPageIntf;
import com.accenture.demo.graphql.service.intf.GroupPageMemberIntf;
import com.accenture.demo.graphql.service.intf.GroupPagePostIntf;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

/**
 * Root resolver from type Mutation Class naming convention will simply be
 * Mutation based on schema Methods and its corresponding return type were based
 * from the elements of the graphql schema Method naming convention
 * ElementName.FirstCharacterToLower] for java standard purpose. Method to call
 * service classes to create/update/delete.
 * 
 * @author atcp ies ta
 *
 */
@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private GroupPageIntf groupPageService;

	@Autowired
	private GroupPagePostIntf groupPagePostService;

	@Autowired
	private GroupPageMemberIntf groupPageMemberService;

	public GroupPage addGroupPage(String name, AboutInput about) {
		return groupPageService.addGroupPage(name, about);
	}

	public GroupPage updateGroupPage(int groupPageId, String name, AboutInput about) {
		return groupPageService.updateGroupPage(groupPageId, name, about);
	}

	public String deleteGroupPage(int groupPageId) {
		return groupPageService.deleteGroupPage(groupPageId);
	}

	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePostInput groupPost) {
		return groupPagePostService.addGroupPagePost(groupPageId, groupPost);
	}

	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePostInput groupPost) {
		return groupPagePostService.updateGroupPagePost(groupPageId, groupPagePostId, groupPost);
	}

	public GroupPage deleteGroupPagePost(int groupPageId, int groupPagePostId) {
		return groupPagePostService.deleteGroupPagePost(groupPageId, groupPagePostId);
	}

	public GroupPage addGroupPageMember(int groupPageId, int userId) {
		return groupPageMemberService.addGroupPageMember(groupPageId, userId);
	}

	public GroupPage deleteGroupPageMember(int groupPageId, int groupPageMemberId) {
		return groupPageMemberService.deleteGroupPageMember(groupPageId, groupPageMemberId);
	}
}
