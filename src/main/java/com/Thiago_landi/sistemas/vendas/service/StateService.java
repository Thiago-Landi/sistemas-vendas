package com.Thiago_landi.sistemas.vendas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.controller.dto.StateDTO;
import com.Thiago_landi.sistemas.vendas.controller.mappers.StateMapper;
import com.Thiago_landi.sistemas.vendas.exceptions.InvalidOperationException;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.repository.CityRepository;
import com.Thiago_landi.sistemas.vendas.repository.StateRepository;
import com.Thiago_landi.sistemas.vendas.validator.StateValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {

	private final StateRepository stateRepository;
	private final CityRepository cityRepository;
	private final StateValidator validator;
	private final StateMapper mapper;
		
	public State save(State state) {
		validator.validate(state);
		return stateRepository.save(state);
	}
	
	public Optional<State> findById(UUID id) {
		return stateRepository.findById(id);
	}
	
	public void delete(State state) {		 
		 if (cityRepository.existsByState(state)) {
		        throw new InvalidOperationException(
		        		"Não é possível excluir um estado que possui cidades associadas.");
		    }
		 
		stateRepository.delete(state);
	}
	
	public void update(UUID id, StateDTO dto) {
		State model = stateRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("O estado com o ID fornecido não existe no banco."));
		
		State stateTemp = mapper.toEntity(dto);
		validator.validate(stateTemp);
		
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
