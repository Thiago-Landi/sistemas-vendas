package com.Thiago_landi.sistemas.vendas.controller.dto;

import jakarta.validation.constraints.Size;

public record StateUpdateDTO(
		@Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
		String name, 
		@Size(min = 2, max = 2, message = "campo fora do tamanho padrão")
		String abbreviation) {

}
