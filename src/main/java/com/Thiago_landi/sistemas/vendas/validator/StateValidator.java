package com.Thiago_landi.sistemas.vendas.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Thiago_landi.sistemas.vendas.exceptions.RegistryDuplicateException;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.repository.StateRepository;

@Component
public class StateValidator {

	@Autowired
	private StateRepository repository;
	
	public void validate(State state) {
		if(thereRegisteredState(state)) {
			throw new RegistryDuplicateException("estado já cadastrado");
		}
	}
	
	private boolean thereRegisteredState(State state) {
		Optional<State> stateOptional = repository.findByNameAndAbbreviation(
				state.getName(), state.getAbbreviation());
		
		if(state.getId() == null) return stateOptional.isPresent();
		
		if(stateOptional.isPresent()) return !state.getId().equals(stateOptional.get().getId());
		
		return false;
	}
}
