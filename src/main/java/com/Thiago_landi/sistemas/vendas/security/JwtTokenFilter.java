package com.Thiago_landi.sistemas.vendas.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider tokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		
		var token = tokenProvider.resolveToken((HttpServletRequest) request);
		if(token != null && !token.trim().isEmpty() && tokenProvider.validateToken(token)) {
			Authentication authentication = tokenProvider.getAuthentication(token);
			if(authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filter.doFilter(request, response);	
	}

}
