package com.marko.shop.service.user.impl.util;

import java.util.Date;
import java.util.List;

import com.marko.shop.data.user.entity.User;
import com.marko.shop.infrastructure.security.JwtClaimConstants;
import com.marko.shop.infrastructure.security.JwtToken;
import com.marko.shop.security.jwt.JwtTokenManager;

public class UserJwtUtil {

	private UserJwtUtil() {
		super();
	}

	public static JwtToken buildJwtToken(User user, List<String> userRoles, JwtTokenManager jwtTokenManager) {
		String accessToken = jwtTokenManager.getJwtBuilder()
				.claim(JwtClaimConstants.USER_NAME, user.getUserName())
				.claim(JwtClaimConstants.USER_ID, user.getId())
				.claim(JwtClaimConstants.USER_ROLES, userRoles)
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenManager.getDefaultAccessTokenLifetime()))
				.compact();
		String refreshToken = jwtTokenManager.getJwtBuilder()
				.claim(JwtClaimConstants.USER_ID, user.getId())
				.claim(JwtClaimConstants.USER_ROLES, userRoles)
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenManager.getDefaultRefreshTokenLifetime()))
				.compact();
		return new JwtToken(accessToken, refreshToken);
	}
	
}
