package com.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.model.GroupPage;
import com.demo.graphql.service.intf.GroupPageIntf;

/**
 * Root resolver from type Query Class naming convention [TypeName]+Resolver
 * Methods and its corresponding return type were based from the elements of the
 * graphql schema Method naming convention
 * get+[ElementName.FirstCharacterToUpper] Method to call service classes to
 * fetch return data.
 * 
 * @author atcp ies ta
 *
 */
@Component
public class QueryResolver implements GraphQLQueryResolver {
	@Autowired
	private GroupPageIntf groupPageService;

	public List<GroupPage> getAllGroupPages() {
		return groupPageService.getAllGroupPages();
	}

	public GroupPage getGroupPageById(int groupPageId) {
		return groupPageService.getGroupPageById(groupPageId);
	}
}
