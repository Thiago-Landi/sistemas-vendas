package com.Thiago_landi.sistemas.vendas.controller.dto;

import com.Thiago_landi.sistemas.vendas.model.City;
import com.Thiago_landi.sistemas.vendas.model.State;

public record CityDTO(String name, String cep, State state) {

	public City mapForCity() {
		City city = new City();
		city.setName(name);
		city.setCep(cep);
		city.setState(state);
		return city;
	}
}
