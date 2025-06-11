package com.Thiago_landi.sistemas.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Thiago_landi.sistemas.vendas.controller.dto.security.AccountCredentialsDTO;
import com.Thiago_landi.sistemas.vendas.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping
	public ResponseEntity<Object> signin(@RequestBody AccountCredentialsDTO credentials){
		if(credentiaisIsInvalid(credentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body("invalid client request!");
		
		var token = authService.signIn(credentials);
		
		if(token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		
		return ResponseEntity.ok().body(token);
	}
	
	private static boolean credentiaisIsInvalid(AccountCredentialsDTO credentials) { return credentials == null ||
	           credentials.login() == null || credentials.login().trim().isEmpty() ||
	           credentials.password() == null || credentials.password().trim().isEmpty();
	}
	
	@PutMapping("{login}")
	public ResponseEntity<Object> refreshToken(
			@PathVariable("login") String login, @RequestHeader("Authorization") String refreshToken){
		
		if(parametersAreInvalid(login, refreshToken)) return ResponseEntity.status(
				HttpStatus.FORBIDDEN).body("Invalid client request!");
		
		var token = authService.refreshToken(login, refreshToken);
		if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body("Invalid client request!");
		
		return ResponseEntity.ok().body(token);
	}

	private boolean parametersAreInvalid(String login, String refreshToken) {
		return login == null || login.trim().isEmpty()
		        || refreshToken == null || refreshToken.trim().isEmpty();
	}
}
