package com.employeemngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DepartmentHistory")
public class DepartmentHistory {
	@Id
	private String id;
	private String name;
	private String Description;

	public String getDescription() {
		return Description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
