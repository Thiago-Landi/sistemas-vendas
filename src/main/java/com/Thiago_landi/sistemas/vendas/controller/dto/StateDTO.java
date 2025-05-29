package com.Thiago_landi.sistemas.vendas.controller.dto;

import com.Thiago_landi.sistemas.vendas.model.State;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StateDTO(
		@NotBlank(message = "campo obrigatorio")
		@Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
		String name, 
		@NotBlank(message = "campo obrigatorio")
		@Size(min = 2, max = 2, message = "campo fora do tamanho padrão")
		String abbreviation) {

	public State mapForState() {
		State state = new State();
		state.setName(this.name);
		state.setAbbreviation(this.abbreviation);
		return state;
	}
}
