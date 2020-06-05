package com.customer;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {

	private static final Logger LOG = Logger.getLogger(CustomerApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Autowired
	public void setEnv(Environment e) {
		LOG.info(e.getProperty("msg"));
		LOG.info(e.getProperty("getProductUrl"));
	}

}
