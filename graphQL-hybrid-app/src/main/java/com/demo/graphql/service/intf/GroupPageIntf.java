package com.demo.graphql.service.intf;

import java.util.List;

import com.demo.graphql.model.AboutInput;
import com.demo.graphql.model.GroupPage;

public interface GroupPageIntf {

	public List<GroupPage> getAllGroupPages();

	public GroupPage getGroupPageById(int groupPageId);

	public GroupPage addGroupPage(String name, AboutInput about);

	public GroupPage updateGroupPage(int groupPageId, String name, AboutInput about);

	public String deleteGroupPage(int groupPageId);

}
