package com.Thiago_landi.sistemas.vendas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.controller.dto.CityDTO;
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
	
	public void update(UUID id, CityDTO dto) {
		City model = cityRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("A cidade com o ID fornecido não existe no banco."));
		
		updateData(model, dto);
		cityRepository.save(model);
	}
	
	private void updateData(City model, CityDTO dto) {
	    if (dto.name() != null) {
	        model.setName(dto.name());
	    }
	    if (dto.cep() != null) {
	        model.setCep(dto.cep());
	    }
	    if (dto.state() != null) {
	        model.setState(dto.state());
	    }
	}
}
