package com.Thiago_landi.sistemas.vendas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
}
