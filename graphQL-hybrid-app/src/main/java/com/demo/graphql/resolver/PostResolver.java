package com.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.Comment;
import com.demo.graphql.model.Post;
import com.demo.graphql.service.intf.CommentIntf;

/**
 * Resolver from type Post Class naming convention [TypeName]+Resolver Methods
 * and its corresponding return type were based from the type Post. Method
 * naming convention get+[ElementName.FirstCharacterToUpper] Method input
 * differs from root resolver which takes literally what is in the schema.
 * Inputs for non root/query resolver includes Resolver Object + graphql schema
 * input. Method to call service classes to fetch return data.
 * Error in Query will capture only for GraphQLException from Service Class and Unknown Exception
 * 
 * @author atcp ies ta
 *
 */
@Component
public class PostResolver implements GraphQLResolver<Post> {

	@Autowired
	private CommentIntf commentService;

	public List<Comment> getComments(Post post, int top) {
		List<Comment> commentList = null;
		try {
			commentList = commentService.getComments(post.getId(), top);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured in getting comment list", e.toString());
		}
		return commentList;
	}
}
