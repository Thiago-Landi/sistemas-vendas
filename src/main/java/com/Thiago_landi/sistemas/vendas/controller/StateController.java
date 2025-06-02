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

import com.Thiago_landi.sistemas.vendas.controller.dto.StateCreatedDTO;
import com.Thiago_landi.sistemas.vendas.controller.dto.StateUpdateDTO;
import com.Thiago_landi.sistemas.vendas.controller.mappers.StateMapper;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.service.StateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController implements GenericController {

	private final StateService stateService;
	private final StateMapper mapper;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid StateCreatedDTO dto){
		State stateModel = mapper.toEntity(dto);
		stateService.save(stateModel);
		
		// esse codigo constroi a url para acessar o author criado (URL:http://localhost:8080/authors/{id})
		URI location = generateHeaderLocation(stateModel.getId());
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StateCreatedDTO> findById(@PathVariable("id") String id){
		var idState = UUID.fromString(id);

		return stateService
				.findById(idState)
				.map(state -> { 
					StateCreatedDTO dto = mapper.toCreateDTO(state);
					return ResponseEntity.ok(dto);
				}).orElseGet( () -> ResponseEntity.notFound().build() );
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
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid StateUpdateDTO dto){
		var idState = UUID.fromString(id);
		
		if(stateService.findById(idState).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		stateService.update(idState, dto);
		return ResponseEntity.noContent().build();
	}
}
