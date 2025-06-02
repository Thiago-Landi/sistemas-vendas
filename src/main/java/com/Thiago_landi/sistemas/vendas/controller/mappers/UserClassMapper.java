package com.Thiago_landi.sistemas.vendas.controller.mappers;

import org.mapstruct.Mapper;

import com.Thiago_landi.sistemas.vendas.controller.dto.UserClassDTO;
import com.Thiago_landi.sistemas.vendas.model.UserClass;

@Mapper(componentModel = "spring")
public interface UserClassMapper {
	
    UserClass toEntity(UserClassDTO dto);
    UserClassDTO toCreateDTO(UserClass user);
}
