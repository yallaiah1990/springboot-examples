package com.accenture.demo.graphql.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GroupPage {

	@Id
	@Column(name = "group_page_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "about_id")
	private About about;
	@OneToMany(mappedBy = "groupPage", cascade = CascadeType.ALL)
	private List<GroupPagePost> groupPosts;
	@OneToMany(mappedBy = "groupPage", cascade = CascadeType.ALL)
	private List<GroupPageMember> members;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public List<GroupPagePost> getGroupPosts() {
		return groupPosts;
	}

	public void setGroupPosts(List<GroupPagePost> groupPosts) {
		this.groupPosts = groupPosts;
	}

	public List<GroupPageMember> getMembers() {
		return members;
	}

	public void setMembers(List<GroupPageMember> members) {
		this.members = members;
	}
}