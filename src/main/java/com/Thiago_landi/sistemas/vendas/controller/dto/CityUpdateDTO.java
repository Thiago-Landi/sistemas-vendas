package com.Thiago_landi.sistemas.vendas.controller.dto;

import com.Thiago_landi.sistemas.vendas.model.State;

import jakarta.validation.constraints.Size;

public record CityUpdateDTO(
		@Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
		String name, 
		@Size(min = 9, max = 9, message = "campo fora do tamanho padrão")
		String cep, 
		State state) {

}
