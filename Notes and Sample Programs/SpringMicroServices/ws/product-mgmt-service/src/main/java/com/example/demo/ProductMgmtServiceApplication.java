package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductMgmtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMgmtServiceApplication.class, args);
	}

}
