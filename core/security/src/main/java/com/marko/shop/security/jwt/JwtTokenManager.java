package com.marko.shop.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenManager {

	@Value("${app.jwt-signing-key}")
	private String jwtSigningKey;
	
	@Value("${app.jwt-access-token-lifetime}")
	private Long defaultAccessTokenLifetime;
	
	@Value("${app.jwt-refresh-token-lifetime}")
	private Long defaultRefreshTokenLifetime;
	
	public JwtBuilder getJwtBuilder() {
		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(jwtSigningKey.getBytes()));
	}
	
	public JwtParser getJwtParser() {
		return Jwts.parser()
				.setSigningKey(Keys.hmacShaKeyFor(jwtSigningKey.getBytes()));
	}

	public Long getDefaultAccessTokenLifetime() {
		return defaultAccessTokenLifetime;
	}

	public Long getDefaultRefreshTokenLifetime() {
		return defaultRefreshTokenLifetime;
	}
}
