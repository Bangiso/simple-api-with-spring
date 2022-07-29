package com.aphiwe.simpleApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@SpringBootApplication
public class SimpleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApiApplication.class, args);
	}

}
