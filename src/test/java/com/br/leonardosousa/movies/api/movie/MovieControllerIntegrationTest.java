package com.br.leonardosousa.movies.api.movie;


import com.br.leonardosousa.movies.api.MoviesApplication;
import com.br.leonardosousa.movies.api.genre.Genre;
import com.br.leonardosousa.movies.api.genre.GenreRepository;
import com.br.leonardosousa.movies.api.productioncompany.ProductionCompany;
import com.br.leonardosousa.movies.api.productioncompany.ProductionCompanyRepository;
import org.apache.tomcat.jni.Global;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.br.leonardosousa.movies.api.movie.TestUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@Transactional
public class MovieControllerIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ProductionCompanyRepository productionCompanyRepository;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MockMvc mockMvc;

    private final String MOVIE_URL = "http://localhost:8080/movies";
    private final String GENRES_URL = "http://localhost:8080/genres";
    private final String COMPANY_URL = "http://localhost:8080/productionCompanies";


    @Test
    public void getAllMovies() throws Exception {

        movieRepository.save(getMovie(1));
        movieRepository.save(getMovie(2));

        List<Movie> movies = (List<Movie>) movieRepository.findAll();

        this.mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$._embedded.movies", hasSize(2)));

    }

    @Test
    public void saveRelationshipGenreOnMovie() throws Exception {

        final Genre genre = getGenre(1);

        final Movie movie = Movie.builder().title("Teste1").imdb("123").originalLanguage("english").build();

        template.postForEntity(GENRES_URL, genre, Genre.class);
        template.postForEntity(MOVIE_URL, movie, Movie.class);

        final Movie movieReult = movieRepository.findByTitle(movie.getTitle()).get(0);
        final Genre genreResult = genreRepository.findByName(genre.getName()).get(0);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");

        HttpEntity<String> entity = new HttpEntity<String>(GENRES_URL+ "/" + genreResult.getId(), requestHeaders);
        template.exchange(MOVIE_URL + "/"+ movieReult.getId() +"/genres", HttpMethod.PUT, entity, String.class);

        String jsonResponse = template.getForObject(MOVIE_URL + "/"+ movieReult.getId() +"/genres", String.class);
        JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
        JSONArray jsonArray = jsonObj.getJSONArray("genres");

        assertEquals("company is incorrect", jsonArray.getJSONObject(0).getString("name"), genre.getName());

    }

    @Test
    public void failToSaveMovie() throws Exception {

        final Movie movie = Movie.builder().title("Teste1").build();

        ResponseEntity<String> response = template.postForEntity(MOVIE_URL, movie, String.class);

        assertEquals("Result is not expected", HttpStatus.BAD_REQUEST,response.getStatusCode());

    }

    @Test
    public void saveRelationshipCompanyOnMovie() throws Exception {

        final ProductionCompany company = getProductionCompany(1);

        final Movie movie = Movie.builder().title("Teste1").imdb("123").originalLanguage("english").build();

        template.postForEntity(COMPANY_URL, company, ProductionCompany.class);
        template.postForEntity(MOVIE_URL, movie, Movie.class);

        final Movie movieReult = movieRepository.findByTitle(movie.getTitle()).get(0);
        final ProductionCompany companyResult = productionCompanyRepository.findByName(company.getName()).get(0);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");

        HttpEntity<String> entity = new HttpEntity<String>(COMPANY_URL+ "/" + companyResult.getId(), requestHeaders);
        template.exchange(MOVIE_URL + "/"+ movieReult.getId() +"/productionCompanies", HttpMethod.PUT, entity, String.class);

        String jsonResponse = template.getForObject(MOVIE_URL + "/"+ movieReult.getId() +"/productionCompanies", String.class);
        JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
        JSONArray jsonArray = jsonObj.getJSONArray("productionCompanies");

        assertEquals("company is incorrect", jsonArray.getJSONObject(0).getString("name"), company.getName());

    }
}
