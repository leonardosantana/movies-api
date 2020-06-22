package com.br.leonardosousa.movies.api.productioncompany;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductionCompanyRepository extends CrudRepository <ProductionCompany, Integer> {

    List<ProductionCompany> findByName(String name);
}
