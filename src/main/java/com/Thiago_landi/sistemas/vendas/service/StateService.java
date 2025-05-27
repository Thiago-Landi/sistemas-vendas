package com.Thiago_landi.sistemas.vendas.service;

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
}
