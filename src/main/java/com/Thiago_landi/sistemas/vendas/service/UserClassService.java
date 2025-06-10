package com.Thiago_landi.sistemas.vendas.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Thiago_landi.sistemas.vendas.model.Permission;
import com.Thiago_landi.sistemas.vendas.model.UserClass;
import com.Thiago_landi.sistemas.vendas.repository.PermissionRepository;
import com.Thiago_landi.sistemas.vendas.repository.UserClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClassService implements UserDetailsService {

	private final UserClassRepository userClassRepository;
	private final PasswordEncoder encoder;
	private final PermissionRepository permissionRepository;
	
	public UserClass save(UserClass userClass, List<String> permissionDescription) {
		userClass.setPassword(encoder.encode(userClass.getPassword()));
		
		List<Permission> permissions = permissionDescription.stream()
				.map(desc -> permissionRepository.findByDescription(desc)
						.orElseThrow(() -> new RuntimeException("Permissão não encontrada: " + desc)))
				.toList();
		userClass.setPermissions(permissions);
				
		return userClassRepository.save(userClass);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		var user = userClassRepository.findByLogin(login);
		if(user != null) return user;
		else throw new UsernameNotFoundException("login " + login + " not found!");
	}
	
	
}
