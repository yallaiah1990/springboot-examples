package com.demo.graphql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GroupPagePost {

	@Id
	@Column(name="group_page_post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="group_page_id")
	private GroupPage groupPage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public GroupPage getGroupPage() {
		return groupPage;
	}
	
	public void setGroupPage(GroupPage groupPage) {
		this.groupPage = groupPage;
	}
}
