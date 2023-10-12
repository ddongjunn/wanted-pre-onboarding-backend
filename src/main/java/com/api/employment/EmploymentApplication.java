package com.api.employment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EmploymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmploymentApplication.class, args);
	}

}
