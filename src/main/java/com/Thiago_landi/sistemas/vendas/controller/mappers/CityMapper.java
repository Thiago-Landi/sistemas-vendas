package com.Thiago_landi.sistemas.vendas.controller.mappers;

import org.mapstruct.Mapper;

import com.Thiago_landi.sistemas.vendas.controller.dto.CityDTO;
import com.Thiago_landi.sistemas.vendas.model.City;

@Mapper(componentModel = "spring")
public interface CityMapper {
	
	City toEntity(CityDTO dto);
	
	CityDTO toDTO(City city);
	
}
