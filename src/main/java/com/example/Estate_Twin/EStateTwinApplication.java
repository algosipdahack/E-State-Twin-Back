package com.example.Estate_Twin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EStateTwinApplication {

	public static void main(String[] args) {

		SpringApplication.run(EStateTwinApplication.class, args);
	}

}
