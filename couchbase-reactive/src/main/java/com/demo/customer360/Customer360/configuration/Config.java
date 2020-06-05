package com.demo.customer360.Customer360.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

	@Value("${database.host}")
	private String hostName;

	@Value("${database.username}")
	private String userName;

	@Value("${database.password}")
	private String password;

	public Cluster getCluster() {
		Cluster cluster = CouchbaseCluster.create("localhost");
		cluster = cluster.authenticate("admin", "admin123");
		return cluster;
	}

}
