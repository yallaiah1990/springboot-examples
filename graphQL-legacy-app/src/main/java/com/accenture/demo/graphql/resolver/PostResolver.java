package com.accenture.demo.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.accenture.demo.graphql.model.Comment;
import com.accenture.demo.graphql.model.Post;
import com.accenture.demo.graphql.service.intf.CommentIntf;
import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Resolver from type Post
 * Class naming convention [TypeName]+Resolver
 * Methods and its corresponding return type were based from the type Post.
 * Method naming convention get+[ElementName.FirstCharacterToUpper]
 * Method input differs from root resolver which takes literally what is in the schema.
 * Inputs for non root/query resolver includes Resolver Object + graphql schema input.
 * Method to call service classes to fetch return data.
 * 
 * @author yallaiah.eswar
 *
 */
@Component
public class PostResolver implements GraphQLResolver<Post> {

	@Autowired
	private CommentIntf commentService;
	
	public List<Comment> getComments(Post post, int top) {
		return commentService.getComments(post.getId(), top);
	}
}
