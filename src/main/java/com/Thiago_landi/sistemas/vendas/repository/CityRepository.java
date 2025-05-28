package com.Thiago_landi.sistemas.vendas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thiago_landi.sistemas.vendas.model.City;

public interface CityRepository extends JpaRepository<City, UUID> {

}
