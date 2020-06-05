package com.accenture.customer360.Customer360.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class converter {

	private final Gson gson = new GsonBuilder().create();

	public <T> T fromJson(String source, Class<T> type) {
		return gson.fromJson(source, type);
	}

	public <T> String toJson(T source) {
		return gson.toJson(source);
	}
}
