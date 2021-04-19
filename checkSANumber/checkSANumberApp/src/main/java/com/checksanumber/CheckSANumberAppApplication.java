package com.checksanumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*
 * Author: Gabriele Ciardo
 * 
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.checksanumber.repository")
public class CheckSANumberAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckSANumberAppApplication.class, args);
	}

}