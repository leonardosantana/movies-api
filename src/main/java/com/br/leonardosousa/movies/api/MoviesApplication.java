package com.br.leonardosousa.movies.api;

import com.br.leonardosousa.movies.api.movie.MovieValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class MoviesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoviesApplication.class, args);

	}

	@Bean
	public MovieValidator beforeCreateMovieValidator(){
		return new MovieValidator();
	};

}
