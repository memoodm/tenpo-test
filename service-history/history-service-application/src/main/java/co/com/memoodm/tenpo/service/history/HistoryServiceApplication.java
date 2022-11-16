package co.com.memoodm.tenpo.service.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HistoryServiceApplication
{

	public static void main(String[] args) {
		SpringApplication.run(HistoryServiceApplication.class, args);
	}

}
