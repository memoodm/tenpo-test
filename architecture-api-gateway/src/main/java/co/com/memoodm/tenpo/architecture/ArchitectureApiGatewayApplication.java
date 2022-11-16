package co.com.memoodm.tenpo.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ArchitectureApiGatewayApplication
{
	public static void main(String[] args) {
		SpringApplication.run(ArchitectureApiGatewayApplication.class, args);
	}
}
