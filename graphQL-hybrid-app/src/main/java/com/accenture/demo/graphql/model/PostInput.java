package com.accenture.demo.graphql.model;

import org.springframework.stereotype.Component;

@Component
public class PostInput {

	private String content;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}