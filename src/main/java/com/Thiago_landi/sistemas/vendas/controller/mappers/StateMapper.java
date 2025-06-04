package com.Thiago_landi.sistemas.vendas.controller.mappers;

import org.mapstruct.Mapper;

import com.Thiago_landi.sistemas.vendas.controller.dto.StateCreatedDTO;
import com.Thiago_landi.sistemas.vendas.controller.dto.StateUpdateDTO;
import com.Thiago_landi.sistemas.vendas.model.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

	 	State toEntity(StateCreatedDTO dto);
	    StateCreatedDTO toCreateDTO(State state);

	    // Para atualização
	    State toEntity(StateUpdateDTO dto);
	    StateUpdateDTO toUpdateDTO(State state);
}
