package com.Thiago_landi.sistemas.vendas.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.model.UserClass;
import com.Thiago_landi.sistemas.vendas.repository.UserClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClassService {

	private final UserClassRepository userClassRepository;
	private final PasswordEncoder encoder;
	
	public UserClass save(UserClass userClass) {
		String password = userClass.getPassword();
		userClass.setPassword(encoder.encode(password));
		return userClassRepository.save(userClass);
	}
}
