package com.Thiago_landi.sistemas.vendas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.controller.dto.StateDTO;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.repository.StateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {

	private final StateRepository stateRepository;
	
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	public Optional<State> findById(UUID id) {
		return stateRepository.findById(id);
	}
	
	public void delete(State state) {
		stateRepository.delete(state);
	}
	
	public void update(UUID id, StateDTO dto) {
		State model = stateRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("O estado com o ID fornecido não existe no banco."));
		
		updateData(model, dto);
		stateRepository.save(model);
	}
	
	private void updateData(State model, StateDTO dto) {
		if (dto.name() != null) {
	        model.setName(dto.name());
	    }
	    if(dto.abbreviation() != null) {
	    	model.setAbbreviation(dto.abbreviation());
	    }
	}
}
