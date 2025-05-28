package com.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class LoanManagementSystemProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementSystemProjectApplication.class, args);
	}

}
