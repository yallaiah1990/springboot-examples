package com.accenture.demo.graphql.service.intf;

import java.util.List;

import com.accenture.demo.graphql.model.GroupPage;
import com.accenture.demo.graphql.model.GroupPagePost;
import com.accenture.demo.graphql.model.GroupPagePostInput;

public interface GroupPagePostIntf {
	public List<GroupPagePost> getGroupPagePosts(int groupPageId, int top);
	
	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePostInput groupPost);

	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePostInput groupPost);

	public GroupPage deleteGroupPagePost(int groupPageId, int groupPagePostId);
}
