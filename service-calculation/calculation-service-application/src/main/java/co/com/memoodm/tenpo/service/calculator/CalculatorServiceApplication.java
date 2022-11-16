package co.com.memoodm.tenpo.service.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EnableFeignClients(value = {"co.com.memoodm.tenpo.service.mock.web.client"})
@EnableAsync
public class CalculatorServiceApplication
{
	public static void main(String[] args) 
	{

		SpringApplication.run(CalculatorServiceApplication.class, args);
	}

}
