package com.br.leonardosousa.movies.api.productioncompany;

import com.br.leonardosousa.movies.api.movie.Movie;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_PRODUCTION_COMPANY")
public class ProductionCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productionCompanySequence")
    private Integer id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy="productionCompanies")
    private List<Movie> movies;

}
