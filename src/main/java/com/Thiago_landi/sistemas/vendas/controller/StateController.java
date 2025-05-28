package com.Thiago_landi.sistemas.vendas.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("{id}")
	public ResponseEntity<StateDTO> findById(@PathVariable("id") String id){
		var idState = UUID.fromString(id);
		Optional<State> stateOptional = stateService.findById(idState);
		if(stateOptional.isPresent()) {
			State state = stateOptional.get();
			StateDTO stateDto = new StateDTO(state.getName(), state.getAbbreviation());
			
			return ResponseEntity.ok(stateDto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		var idState = UUID.fromString(id);
		Optional<State> state = stateService.findById(idState);
		
		if(state.isEmpty()) return ResponseEntity.notFound().build();
		
		stateService.delete(state.get());
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody StateDTO dto){
		var idState = UUID.fromString(id);
		
		if(stateService.findById(idState).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		stateService.update(idState, dto);
		return ResponseEntity.noContent().build();
	}
}
