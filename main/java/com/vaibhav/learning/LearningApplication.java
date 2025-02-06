package com.vaibhav.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext applicationContext = SpringApplication.run(LearningApplication.class, args);
		System.out.println("\nApplication Context Called, Beans are scanned and created\n");
	}

}
