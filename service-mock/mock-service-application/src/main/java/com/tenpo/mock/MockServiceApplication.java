package com.tenpo.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class MockServiceApplication
{
	public static void main(String[] args) 
	{
		SpringApplication.run(MockServiceApplication.class, args);
	}

}
