package com.squad.dezktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BlueBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueBankApplication.class, args);
	}

}
