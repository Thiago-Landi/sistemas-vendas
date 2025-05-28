package com.Thiago_landi.sistemas.vendas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.model.City;
import com.Thiago_landi.sistemas.vendas.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository cityRepository;
	
	public City save(City city) {
		return cityRepository.save(city);
	}
	
	public Optional<City> findById(UUID id){
		return cityRepository.findById(id);
	}
	
	public void delete(City city) {
		cityRepository.delete(city);
	}
}
