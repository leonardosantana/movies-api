package com.br.leonardosousa.movies.api.movie;

import com.br.leonardosousa.movies.api.genre.Genre;
import com.br.leonardosousa.movies.api.productioncompany.ProductionCompany;

public class TestUtils {

    public static Movie getMovie() {
        return Movie.builder().id(1).build();
    }

    public static Movie getMovie(Integer id) {
        return Movie.builder().id(id).build();
    }

    public static Genre getGenre(Integer id) {
        return Genre.builder().id(id).name("TERROR").build();
    }

    public static ProductionCompany getProductionCompany(Integer id) {
        return ProductionCompany.builder().id(id).name("SONY").build();
    }
}
