package com.demo.graphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.graphql.dao.GroupPageDAO;
import com.demo.graphql.exception.GraphQLException;
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
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public List<GroupPage> getAllGroupPages() {
		// TODO Auto-generated method stub
		List<GroupPage> groupPages = null;
		try {
			groupPages = groupPageDAO.getAllGroupPages();
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page service processing",e.toString());
		}
		return groupPages;
	}

	/**
	 * Call class that will trigger db query to get GroupPage base on groupPageId
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public GroupPage getGroupPageById(int groupPageId) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			groupPage = groupPageDAO.getGroupPageById(groupPageId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page service processing",e.toString());
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db insert to create new GroupPage
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public GroupPage addGroupPage(String name, AboutInput about) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			About aboutNew = new About();
			aboutNew.setDescription(about.getDescription());
			aboutNew.setHistory(about.getHistory());
			aboutNew.setPrivacy(about.getPrivacy());
			aboutNew.setVisibility(about.getVisibility());
			groupPage = groupPageDAO.addGroupPage(name, aboutNew);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page service processing",e.toString());
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db update of existing GroupPage
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public GroupPage updateGroupPage(int groupPageId, String name, AboutInput about) {
		// TODO Auto-generated method stub
		GroupPage groupPage = null;
		try {
			About aboutUpdate = new About();
			aboutUpdate.setDescription(about.getDescription());
			aboutUpdate.setHistory(about.getHistory());
			aboutUpdate.setPrivacy(about.getPrivacy());
			aboutUpdate.setVisibility(about.getVisibility());
			groupPage = groupPageDAO.updateGroupPage(groupPageId, name, aboutUpdate);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page service processing",e.toString());
		}
		return groupPage;
	}

	/**
	 * Call class that will trigger db delete of existing GroupPage
	 * Error in method will capture GraphQLException from previous class and do a rethrow of the same error.
	 * Default failure handler will be added as well which will throw a GraphQLException
	 */
	@Override
	public String deleteGroupPage(int groupPageId) {
		// TODO Auto-generated method stub
		String message = null;
		try {
			message = groupPageDAO.deleteGroupPage(groupPageId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Error in group page service processing",e.toString());
		}
		return message;
	}

}
