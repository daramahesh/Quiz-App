package com.marc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	@Autowired
	private WebClient webClient;
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
