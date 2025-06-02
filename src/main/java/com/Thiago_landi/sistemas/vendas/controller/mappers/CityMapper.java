package com.Thiago_landi.sistemas.vendas.controller.mappers;

import org.mapstruct.Mapper;

import com.Thiago_landi.sistemas.vendas.controller.dto.CityCreatedDTO;
import com.Thiago_landi.sistemas.vendas.controller.dto.CityUpdateDTO;
import com.Thiago_landi.sistemas.vendas.model.City;

@Mapper(componentModel = "spring")
public interface CityMapper {
	
	// Para criação
    City toEntity(CityCreatedDTO dto);
    CityCreatedDTO toCreateDTO(City city);

    // Para atualização
    City toEntity(CityUpdateDTO dto);
    CityUpdateDTO toUpdateDTO(City city);
	
}
