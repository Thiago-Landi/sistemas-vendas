package com.Thiago_landi.sistemas.vendas.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Thiago_landi.sistemas.vendas.controller.dto.StateDTO;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.service.StateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController implements GenericController {

	private final StateService stateService;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody StateDTO state){
		State stateModel = state.mapForState();
		stateService.save(stateModel);
		
		// esse codigo constroi a url para acessar o author criado (URL:http://localhost:8080/authors/{id})
		URI location = generateHeaderLocation(stateModel.getId());
		return ResponseEntity.created(location).build();
	}
}
