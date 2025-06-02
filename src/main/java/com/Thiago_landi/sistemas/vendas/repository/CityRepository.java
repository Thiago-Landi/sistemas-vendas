package com.Thiago_landi.sistemas.vendas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thiago_landi.sistemas.vendas.model.City;
import com.Thiago_landi.sistemas.vendas.model.State;

public interface CityRepository extends JpaRepository<City, UUID> {
    boolean existsByState(State state);
    
    Optional<City> findByNameAndCep(String name, String cep); 

}
