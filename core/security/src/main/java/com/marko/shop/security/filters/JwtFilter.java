package com.marko.shop.security.filters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.marko.shop.infrastructure.security.AuthenticatedUser;
import com.marko.shop.infrastructure.security.JwtClaimConstants;
import com.marko.shop.security.jwt.JwtTokenManager;

import io.jsonwebtoken.Claims;

public class JwtFilter implements Filter {

	private final JwtTokenManager jwtManager;
	
    public JwtFilter(JwtTokenManager jwtManager) {
		this.jwtManager = jwtManager;
	}

	@SuppressWarnings("unchecked")
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	HttpServletResponse httpResponse = (HttpServletResponse) response;
    	
    	String authorizationHeader = httpRequest.getHeader("Authorization");
    	
    	if(authorizationHeader == null) {
    		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header not found!");
    		return;
    	}
    	
    	if(!authorizationHeader.contains("Bearer ")) {
    		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bearer token not provided!");
    		return;
    	}
    	
    	String bearerToken = authorizationHeader.split(" ")[1];
    	
    	try {
    		Claims claims = (Claims) jwtManager.getJwtParser().parse(bearerToken).getBody();
    		Integer userId = (Integer) claims.get(JwtClaimConstants.USER_ID);
    		String userName = (String) claims.get(JwtClaimConstants.USER_NAME);
    		List<String> roles = (List<String>) claims.get(JwtClaimConstants.USER_ROLES);
    		List<GrantedAuthority> authorities = roles.stream()
    				.map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
    		AuthenticatedUser principal = new AuthenticatedUser();
    		principal.setUserId(userId.longValue());
    		principal.setUserName(userName);
    		principal.setAuthorities(authorities);
    		UsernamePasswordAuthenticationToken authToken = 
    				new UsernamePasswordAuthenticationToken(principal, null, authorities);
    		SecurityContextHolder.getContext().setAuthentication(authToken);
    		chain.doFilter(httpRequest, httpResponse);
    	} catch(Exception ex) {
    		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    	}
    }

}
