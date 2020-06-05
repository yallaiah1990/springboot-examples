package com.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableDiscoveryClient
public class InventoryDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryDetailsApplication.class, args);
	}

	@Autowired                                              
	public void setEnv(Environment e)                       
	{                                                       
		System.out.println(e.getProperty("msg"));           
	}                                                       
	                                                        
}
