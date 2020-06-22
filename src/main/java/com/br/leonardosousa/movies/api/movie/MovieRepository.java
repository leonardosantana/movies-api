package com.br.leonardosousa.movies.api.movie;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findByTitle(String title);

}
