package com.br.leonardosousa.movies.api.movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.br.leonardosousa.movies.api.movie.TestUtils.getMovie;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Integration tests for Spring Data based {@link MovieRepository}.
 *
 * @author Leonardo Sousa
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryIntegrationTest {

    @Autowired
    private MovieRepository sut;

    @Test
    public void findsAllMovies(){

        sut.save(getMovie());
        Iterable<Movie> movies = sut.findAll();

        assertThat(movies).isNotEmpty();

    }

    @Test
    public void saveOneMovie(){

        Movie movie = Movie.builder().title("save one title").build();
        sut.save(movie);

        assertThat(sut.findByTitle(movie.getTitle()).get(0).getTitle()).isEqualTo(movie.getTitle());

    }

    @Test
    public void findMovieById(){

        Movie movie = Movie.builder().title("some title").build();
        sut.save(movie);

        Movie findMovie = sut.findByTitle(movie.getTitle()).get(0);
        assertThat(movie.getTitle()).isEqualTo(findMovie.getTitle());
    }

    @Test
    public void deleteOneMovie(){

        Movie movie = getMovie();
        sut.save(movie);

        sut.delete(getMovie());

    }




}
