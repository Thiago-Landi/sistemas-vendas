package com.Thiago_landi.sistemas.vendas.controller.mappers;

import org.mapstruct.Mapper;

import com.Thiago_landi.sistemas.vendas.controller.dto.StateDTO;
import com.Thiago_landi.sistemas.vendas.model.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

	State toEntity(StateDTO dto);
	
	StateDTO toDTO(State state);
}
