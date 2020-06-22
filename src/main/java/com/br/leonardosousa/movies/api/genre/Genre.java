package com.br.leonardosousa.movies.api.genre;

import com.br.leonardosousa.movies.api.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_GENRE")
public class Genre implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genreSequence")
    private Integer id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy="genres")
    private List<Movie> movies;



}

