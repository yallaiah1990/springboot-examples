package com.demo.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.graphql.exception.GraphQLException;
import com.demo.graphql.model.AboutInput;
import com.demo.graphql.model.Address;
import com.demo.graphql.model.CommentInput;
import com.demo.graphql.model.GroupPage;
import com.demo.graphql.model.GroupPagePost;
import com.demo.graphql.model.GroupPagePostInput;
import com.demo.graphql.model.Post;
import com.demo.graphql.model.PostInput;
import com.demo.graphql.model.User;
import com.demo.graphql.service.intf.CommentIntf;
import com.demo.graphql.service.intf.GroupPageIntf;
import com.demo.graphql.service.intf.GroupPageMemberIntf;
import com.demo.graphql.service.intf.GroupPagePostIntf;
import com.demo.graphql.service.intf.PostIntf;
import com.demo.graphql.service.intf.UserIntf;

/**
 * Root resolver from type Mutation Class naming convention will simply be
 * Mutation based on schema Methods and its corresponding return type were based
 * from the elements of the graphql schema Method naming convention
 * ElementName.FirstCharacterToLower] for java standard purpose. Method to call
 * service classes to create/update/delete.
 * Error in Mutation will capture only for GraphQLException from Service Class and Unknown Exception
 * 
 * @author atcp ies ta
 *
 */
@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private UserIntf userService;

	@Autowired
	private PostIntf postService;

	@Autowired
	private CommentIntf commentService;
	
	@Autowired
	private GroupPageIntf groupPageService;

	@Autowired
	private GroupPagePostIntf groupPagePostService;

	@Autowired
	private GroupPageMemberIntf groupPageMemberService;

	public User addUser(String name, Address address) {
		User user = null;
		try {
			user = userService.addUser(name, address);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return user;
	}

	public String deleteUser(int userId) {
		String message = null;
		try {
			message = userService.deleteUser(userId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return message;
	}

	public User addPost(int userId, PostInput postInput) {
		User user = null;
		try {
			user = postService.addPost(userId, postInput);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return user;
	}

	public User updatePost(int userId, int postId, PostInput postInput) {
		User user = null;
		try {
			user = postService.updatePost(userId, postId, postInput);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return user;
	}

	public User deletePost(int userId, int postId) {
		User user = null;
		try {
			user = postService.deletePost(userId, postId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return user;
	}

	public Post addComment(int postId, CommentInput commentInput) {
		Post post = null;
		try {
			post = commentService.addComment(postId, commentInput);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return post;
	}

	public Post updateComment(int postId, int commentId, CommentInput commentInput) {
		Post post = null;
		try {
			post = commentService.updateComment(postId, commentId, commentInput);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return post;
	}

	public Post deleteComment(int postId, int commentId) {
		Post post = null;
		try {
			post = commentService.deleteComment(postId, commentId);
		} catch (GraphQLException e) {
			throw e;
		} catch (Exception e) {
			throw new GraphQLException("Unknown error occured", e.toString());
		}
		return post;
	}

	public GroupPage addGroupPage(String name, AboutInput about) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPageService.addGroupPage(name, about);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in adding group page",e.toString());
		}
		return groupPage;
	}

	public GroupPage updateGroupPage(int groupPageId, String name, AboutInput about) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPageService.updateGroupPage(groupPageId, name, about);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in updating group page",e.toString());
		}
		return groupPage;
	}

	public String deleteGroupPage(int groupPageId) {
		String message = null;
		try {
			message = groupPageService.deleteGroupPage(groupPageId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in deleting group page",e.toString());
		}
		return message;
	}

	public GroupPagePost addGroupPagePost(int groupPageId, GroupPagePostInput groupPost) {
		GroupPagePost groupPagePost = null;
		try {
			groupPagePost = groupPagePostService.addGroupPagePost(groupPageId, groupPost);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in adding group page post",e.toString());
		}
		return groupPagePost;
	}

	public GroupPagePost updateGroupPagePost(int groupPageId, int groupPagePostId, GroupPagePostInput groupPost) {
		GroupPagePost groupPagePost = null;
		try {
			groupPagePost = groupPagePostService.updateGroupPagePost(groupPageId, groupPagePostId, groupPost);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in updating group page post",e.toString());
		}
		return groupPagePost; 
	}

	public GroupPage deleteGroupPagePost(int groupPageId, int groupPagePostId) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPagePostService.deleteGroupPagePost(groupPageId, groupPagePostId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in deleting group page post",e.toString());
		}
		return groupPage;
	}

	public GroupPage addGroupPageMember(int groupPageId, int userId) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPageMemberService.addGroupPageMember(groupPageId, userId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in adding group page member",e.toString());
		}
		return groupPage;
	}

	public GroupPage deleteGroupPageMember(int groupPageId, int groupPageMemberId) {
		GroupPage groupPage = null;
		try {
			groupPage = groupPageMemberService.deleteGroupPageMember(groupPageId, groupPageMemberId);
		}
		catch(GraphQLException e) {
			throw e;
		}
		catch(Exception e) {
			throw new GraphQLException("Unknown error occured in adding group page member",e.toString());
		}
		return groupPage;
	}
}
