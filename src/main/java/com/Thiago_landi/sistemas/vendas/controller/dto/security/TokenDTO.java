package com.Thiago_landi.sistemas.vendas.controller.dto.security;

import java.util.Date;


public record TokenDTO (
		String Login, 
		Boolean authenticated, 
		Date created, 
		Date expiration, 
		String accessToken, 
		String refreshToken ) {

}
