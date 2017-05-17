package br.com.blas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HemoblasDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemoblasDiscoveryServerApplication.class, args);
	}
	
}
