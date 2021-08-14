package com.aphiwe.simpleApi;

import com.aphiwe.simpleApi.api.StudentController;
import com.aphiwe.simpleApi.dao.StudentDao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication

@ComponentScan(basePackageClasses = StudentController.class)
@ComponentScan(basePackageClasses = StudentDao.class)
public class SimpleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApiApplication.class, args);
	}

}
