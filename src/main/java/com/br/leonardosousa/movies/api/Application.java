package com.br.leonardosousa.movies.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Value("${welcome.message}")
	public String welcomeMessage;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
