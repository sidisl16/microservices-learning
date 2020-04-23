package com.sid.learning.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SidLearningEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SidLearningEurekaApplication.class, args);
	}

}
