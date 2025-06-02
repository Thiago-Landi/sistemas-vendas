package com.Thiago_landi.sistemas.vendas.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Thiago_landi.sistemas.vendas.exceptions.RegistryDuplicateException;
import com.Thiago_landi.sistemas.vendas.model.City;
import com.Thiago_landi.sistemas.vendas.repository.CityRepository;

@Component
public class CityValidator {

	@Autowired
	private CityRepository cityRepository;
	
	public void validate(City city) {
		if(thereRegisteredCity(city)) {
			throw new RegistryDuplicateException("cidade já cadastrada");
		}
	}
	
	private boolean thereRegisteredCity(City city) {
		Optional<City> cityOptional = cityRepository
				.findByNameAndCep(city.getName(), city.getCep());
		
		if(city.getId() == null) return cityOptional.isPresent();
		
		if(cityOptional.isPresent()) return !city.getId().equals(cityOptional.get().getId());
		
		return false;
	}
}
