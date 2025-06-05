package com.Thiago_landi.sistemas.vendas.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Thiago_landi.sistemas.vendas.controller.dto.security.TokenDTO;
import com.Thiago_landi.sistemas.vendas.exceptions.InvalidJwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
    @Value("${security.jwt.token.expire-lenght:3600000}")
	private long validityInMilliseconds = 3600000; 
    
    private final UserDetailsService userDetailsService;
    
    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
    	secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    	algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
    
    public TokenDTO createAccessToken(String login, List<String> roles) {
    	Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String acessToken = getAccessToken(login, roles, now, validity);
		String refreshToken = getRefreshToken(login, roles, now);
		return new TokenDTO(login, true, now, validity, acessToken, refreshToken);
    }

	private String getRefreshToken(String login, List<String> roles, Date now) {
		Date refreshTokenValidity = new Date(now.getTime() + (validityInMilliseconds * 3));
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(refreshTokenValidity)
				.withSubject(login)
				.sign(algorithm);
		
	}

	private String getAccessToken(String login, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
        		.withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(login)
                .withIssuer(issuerUrl)
        		.sign(algorithm);
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodeToken(token);
		UserDetails userDetails = this.userDetailsService
				.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); 
	}

	private DecodedJWT decodeToken(String token) {
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		} else {
			throw new InvalidJwtAuthenticationException("Invalid JWT token!");
		}
	}
	
	public boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodeToken(token);
	
		try {
			if(decodedJWT.getExpiresAt().before(new Date())) return false;
			return true;
				
		}catch(Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or Invalid JWT Token!");
		}	
	}
    
}
