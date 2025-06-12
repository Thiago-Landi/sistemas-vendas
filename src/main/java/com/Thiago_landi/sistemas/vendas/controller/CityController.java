package com.Thiago_landi.sistemas.vendas.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Thiago_landi.sistemas.vendas.controller.dto.CityCreatedDTO;
import com.Thiago_landi.sistemas.vendas.controller.dto.CityUpdateDTO;
import com.Thiago_landi.sistemas.vendas.controller.mappers.CityMapper;
import com.Thiago_landi.sistemas.vendas.model.City;
import com.Thiago_landi.sistemas.vendas.model.State;
import com.Thiago_landi.sistemas.vendas.service.CityService;
import com.Thiago_landi.sistemas.vendas.service.StateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController implements GenericController{

	private final CityService cityService;
	private final StateService stateService;
	private final CityMapper mapper;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> save(@RequestBody @Valid CityCreatedDTO dto){
		//verificação se existe o state
		var idState = dto.state().getId();
		Optional<State> stateOptional = stateService.findById(idState);
		
		if(stateOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado com ID " + idState + " não encontrado");
		}
		
		//salvando city 
		
		City cityModel = mapper.toEntity(dto);
		cityService.save(cityModel);
		
		URI location = generateHeaderLocation(cityModel.getId());
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'OPERADOR')")
	public ResponseEntity<CityCreatedDTO> findById(@PathVariable("id") String id){
		var idCity = UUID.fromString(id);
		
		return cityService
					.findById(idCity)
					.map(city -> {
						CityCreatedDTO dto = mapper.toCreateDTO(city);
						return ResponseEntity.ok(dto);
					}).orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		var idCity = UUID.fromString(id);
		Optional<City> cityOptional = cityService.findById(idCity);
		
		if(cityOptional.isEmpty()) return ResponseEntity.notFound().build();
		
		cityService.delete(cityOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid CityUpdateDTO dto){
		var idCity = UUID.fromString(id);
		
		if(cityService.findById(idCity).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		cityService.update(idCity, dto);
		return ResponseEntity.noContent().build();
	}
	
}
