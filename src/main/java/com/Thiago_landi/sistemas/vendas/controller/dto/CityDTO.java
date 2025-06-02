package com.Thiago_landi.sistemas.vendas.controller.dto;

import com.Thiago_landi.sistemas.vendas.model.State;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CityDTO(
		@NotBlank(message = "campo obrigatorio")
		@Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
		String name, 
		@NotBlank(message = "campo obrigatorio")
		@Size(min = 9, max = 9, message = "campo fora do tamanho padrão")
		String cep, 
		@jakarta.validation.constraints.NotNull(message = "campo obrigatorio")
		State state) {

}
