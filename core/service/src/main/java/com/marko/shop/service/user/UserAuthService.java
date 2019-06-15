package com.marko.shop.service.user;

import com.marko.shop.data.user.entity.User;

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
	User registerUser(User user);
	
	/**
	 * Register a new employee.
	 * 
	 * @param employee new employee to be registred in the system
	 * 
	 * @return registrated employee
	 */
	User registerEmployee(User employee);
	
	/**
	 * Logs in to the system with username and password
	 * credentials.
	 * 
	 * @param username user name of the user account
	 * 
	 * @param password passowrd of the user account
	 * 
	 * @return existing user
	 */
	User logIn(String username, String password);
	
}
