package com.marko.shop.service.user;

import com.marko.shop.data.user.entity.RoleType;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.infrastructure.security.JwtToken;

/**
 * Manages user registration and login.
 * 
 * @author Marko Vuckovic
 *
 */
public interface UserAuthService {

	/**
	 * Register a new user.
	 * 
	 * @param user new user to be registred in the system
	 * 
	 * @return registrated user
	 */
	User register(User user, RoleType type);
	
	/**
	 * Logs in to the system with username and password
	 * credentials.
	 * 
	 * @param username user name of the user account
	 * 
	 * @param password passowrd of the user account
	 * 
	 * @return JWT tokens
	 */
	JwtToken logIn(String username, String password);

	/**
	 * Refreshes the access and refresh token of an user.
	 * 
	 * @param refreshToken refresh token of an user
	 * 
	 * @return JWT tokens
	 */
	JwtToken refreshTokens(String refreshToken);
	
}
