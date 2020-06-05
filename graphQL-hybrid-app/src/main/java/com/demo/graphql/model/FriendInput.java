package com.demo.graphql.model;

import org.springframework.stereotype.Component;

@Component
public class FriendInput {

	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
