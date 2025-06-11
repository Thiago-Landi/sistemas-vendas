package com.Thiago_landi.sistemas.vendas.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.controller.dto.security.AccountCredentialsDTO;
import com.Thiago_landi.sistemas.vendas.controller.dto.security.TokenDTO;
import com.Thiago_landi.sistemas.vendas.repository.UserClassRepository;
import com.Thiago_landi.sistemas.vendas.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserClassRepository userRepository;
	
	public TokenDTO signIn(AccountCredentialsDTO credentials) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				credentials.login(), credentials.password()));
		
		var user = userRepository.findByLogin(credentials.login());
		if(user == null) throw new UsernameNotFoundException("Login " + credentials.login() + " not found!");
		
		var token = tokenProvider.createAccessToken(credentials.login(), user.getRoles());
		
		return token;
	}
	
	public TokenDTO refreshToken(String login, String refreshToken) {
		var user = userRepository.findByLogin(login);
		TokenDTO token;
		if(user != null) {
			token = tokenProvider.refreshToken(login, refreshToken);
		}else {
            throw new UsernameNotFoundException("Login " + login + " not found!");
		}
		return token;
	}
	
}
