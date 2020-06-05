package com.accenture.demo.graphql.service.intf;

import java.util.List;

import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPageMember;

public interface GroupPageMemberIntf {
	public List<GroupPageMember> getGroupPageMembers(int groupPageId, int top);
	
	public GroupPage addGroupPageMember(int groupPageId, int userId);

	public GroupPage deleteGroupPageMember(int groupPageId, int groupPageMemberId);
}
