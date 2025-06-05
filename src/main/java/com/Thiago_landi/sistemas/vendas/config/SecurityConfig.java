package com.Thiago_landi.sistemas.vendas.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.Thiago_landi.sistemas.vendas.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenProvider tokenProvider;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
				"", 8, 185000, 
				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		
		Map<String, PasswordEncoder> enconders = new HashMap<>();
		
		enconders.put("pbkdf2", pbkdf2Encoder);
		
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", enconders);
		return passwordEncoder ;
	}
}
