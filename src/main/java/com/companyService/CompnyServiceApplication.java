package com.companyService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class CompnyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompnyServiceApplication.class, args);
	}

}
