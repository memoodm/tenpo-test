package co.com.memoodm.tenpo.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ArchitectureRegistryEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchitectureRegistryEurekaApplication.class, args);
	}

}
