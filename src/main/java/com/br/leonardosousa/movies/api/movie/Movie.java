package com.br.leonardosousa.movies.api.movie;

import com.br.leonardosousa.movies.api.genre.Genre;
import com.br.leonardosousa.movies.api.productioncompany.ProductionCompany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_MOVIE")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieSequence")
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull
    private String imdb;

    @NotNull
    private boolean adult;

    @NotNull
    private double budget;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private int votes;

    @NotNull
    private double voteAverage;

    @NotNull
    private String originalLanguage;

    @NotNull
    private String overview;

    @ManyToMany
    @JoinTable(name = "TB_MOVIE_GENRE",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "TB_MOVIE_PRODUCTION_COMPANY",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "production_company_id")})
    private List<ProductionCompany> productionCompanies;

}
