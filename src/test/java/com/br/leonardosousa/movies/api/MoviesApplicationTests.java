package com.br.leonardosousa.movies.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class)
@ActiveProfiles("dev")
public class MoviesApplicationTests {

	@Test
	void contextLoads() {
	}

}
