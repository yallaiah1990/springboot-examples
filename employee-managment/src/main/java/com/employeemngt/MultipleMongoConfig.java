package com.employeemngt;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

//@Configuration
//@EnableMongoRepositories(basePackages = "com.employeemngt.repository", mongoTemplateRef = "primaryMongoTemplate")
public class MultipleMongoConfig {

	@Primary
	@Bean(name = "primary")
	@ConfigurationProperties(prefix = "spring.data.mongodb")
	public MongoProperties getPrimary() {
		return new MongoProperties();
	}

	@Primary
	@Bean(name = "primaryMongoTemplate")
	public MongoTemplate primaryMongoTemplate() {
		return new MongoTemplate(primaryFactory(getPrimary()));
	}

	@Bean
	@Primary
	public MongoDbFactory primaryFactory(final MongoProperties mongo) {
		return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}
}