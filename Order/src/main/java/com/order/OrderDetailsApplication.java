package com.order;

import com.order.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "ping-server", configuration = RibbonConfiguration.class)
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class OrderDetailsApplication {

	public static void main(String[] args) {

		String bootstrapServers = "127.0.0.1:9092";

		SpringApplication.run(OrderDetailsApplication.class, args);

	}


}
