package com.aeroflot.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories("com.aeroflot.webapp.repositories")
public class AeroflotApp {

	public static void main(String[] args) {
		SpringApplication.run(AeroflotApp.class, args);
	}

}
