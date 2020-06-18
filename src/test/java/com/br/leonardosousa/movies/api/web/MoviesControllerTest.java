package com.br.leonardosousa.movies.api.web;

import com.br.leonardosousa.movies.api.ApplicationTests;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MoviesControllerTest extends ApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @Before
    public void setUp(){

        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

    }

}
