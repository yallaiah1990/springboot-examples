package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.dao.GroupPageDAO;
import com.demo.graphql.model.About;
import com.demo.graphql.model.AboutInput;
import com.demo.graphql.model.GroupPage;
import com.demo.graphql.service.intf.GroupPageIntf;

@Service
public class GroupPageImpl implements GroupPageIntf {

	@Autowired
	private GroupPageDAO groupPageDAO;
	
	/**
	 * Call class that will trigger db query to get list of GroupPage on a specific
	 */
	@Override
	public List<GroupPage> getAllGroupPages() {
		// TODO Auto-generated method stub
		return groupPageDAO.getAllGroupPages();
	}

	/**
	 * Call class that will trigger db query to get GroupPage base on groupPageId
	 */
	@Override
	public GroupPage getGroupPageById(int groupPageId) {
		// TODO Auto-generated method stub
		return groupPageDAO.getGroupPageById(groupPageId);
	}

	/**
	 * Call class that will trigger db insert to create new GroupPage
	 */
	@Override
	public GroupPage addGroupPage(String name, AboutInput about) {
		// TODO Auto-generated method stub
		About aboutNew = new About();
		aboutNew.setDescription(about.getDescription());
		aboutNew.setHistory(about.getHistory());
		aboutNew.setPrivacy(about.getPrivacy());
		aboutNew.setVisibility(about.getVisibility());
		return groupPageDAO.addGroupPage(name, aboutNew);
	}

	/**
	 * Call class that will trigger db update of existing GroupPage
	 */
	@Override
	public GroupPage updateGroupPage(int groupPageId, String name, AboutInput about) {
		// TODO Auto-generated method stub
		About aboutUpdate = new About();
		aboutUpdate.setDescription(about.getDescription());
		aboutUpdate.setHistory(about.getHistory());
		aboutUpdate.setPrivacy(about.getPrivacy());
		aboutUpdate.setVisibility(about.getVisibility());
		return groupPageDAO.updateGroupPage(groupPageId, name, aboutUpdate);
	}

	/**
	 * Call class that will trigger db delete of existing GroupPage
	 */
	@Override
	public String deleteGroupPage(int groupPageId) {
		// TODO Auto-generated method stub
		return groupPageDAO.deleteGroupPage(groupPageId);
	}

}
