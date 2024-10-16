package com.crio.learning_navigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LearningNavigatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningNavigatorApplication.class, args);
	}

}
