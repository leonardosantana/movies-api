package com.br.leonardosousa.movies.api.genre;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Integer > {

    List<Genre> findByName(String name);

}
