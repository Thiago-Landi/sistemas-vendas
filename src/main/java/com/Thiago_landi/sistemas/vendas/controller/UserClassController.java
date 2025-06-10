package com.Thiago_landi.sistemas.vendas.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Thiago_landi.sistemas.vendas.controller.dto.UserClassDTO;
import com.Thiago_landi.sistemas.vendas.controller.mappers.UserClassMapper;
import com.Thiago_landi.sistemas.vendas.model.UserClass;
import com.Thiago_landi.sistemas.vendas.service.UserClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserClassController implements GenericController {

	private final UserClassService userClassService;
	private final UserClassMapper mapper;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody UserClassDTO dto){
		UserClass userModel = mapper.toEntity(dto);
		userClassService.save(userModel, dto.permissions());
		
		URI location = generateHeaderLocation(userModel.getId());
		return ResponseEntity.created(location).build();
	}
}
