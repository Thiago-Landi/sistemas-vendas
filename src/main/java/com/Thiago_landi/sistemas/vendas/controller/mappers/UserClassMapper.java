package com.Thiago_landi.sistemas.vendas.controller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.Thiago_landi.sistemas.vendas.controller.dto.UserClassDTO;
import com.Thiago_landi.sistemas.vendas.model.Permission;
import com.Thiago_landi.sistemas.vendas.model.UserClass;

@Mapper(componentModel = "spring")
public interface UserClassMapper {

	@Mapping(target = "permissions", ignore = true) // para toEntity, vamos setar manualmente as permissões
    UserClass toEntity(UserClassDTO dto);

    @Mapping(target = "permissions", source = "permissions")
    UserClassDTO toDTO(UserClass entity);

    // Método para mapear Permission para String (description)
    default String map(Permission permission) {
        return permission.getDescription();
    }

    // Método para mapear lista Permission -> lista String
    default List<String> map(List<Permission> permissions) {
        return permissions.stream()
            .map(this::map)
            .collect(Collectors.toList());
    }
}

