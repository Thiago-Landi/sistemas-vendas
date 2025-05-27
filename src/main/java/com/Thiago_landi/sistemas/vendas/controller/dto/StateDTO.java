package com.Thiago_landi.sistemas.vendas.controller.dto;

import com.Thiago_landi.sistemas.vendas.model.State;

public record StateDTO(String name, String abbreviation) {

	public State mapForState() {
		State state = new State();
		state.setName(this.name);
		state.setAbbreviation(this.abbreviation);
		return state;
	}
}
