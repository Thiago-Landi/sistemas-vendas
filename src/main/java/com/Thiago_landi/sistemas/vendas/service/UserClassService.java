package com.Thiago_landi.sistemas.vendas.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.model.UserClass;
import com.Thiago_landi.sistemas.vendas.repository.UserClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClassService implements UserDetailsService {

	private final UserClassRepository userClassRepository;
	private final PasswordEncoder encoder;
	
	public UserClass save(UserClass userClass) {
		String password = userClass.getPassword();
		userClass.setPassword(encoder.encode(password));
		return userClassRepository.save(userClass);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		var user = userClassRepository.findByLogin(login);
		if(user != null) return user;
		else throw new UsernameNotFoundException("login " + login + " not found!");
	}
	
	
}
