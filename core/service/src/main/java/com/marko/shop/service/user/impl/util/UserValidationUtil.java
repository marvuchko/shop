package com.marko.shop.service.user.impl.util;

import java.util.Optional;

import com.marko.shop.service.exception.InvalidDataException;

public class UserValidationUtil {

	private UserValidationUtil() {
		super();
	}

	public static void checkIfUsernameOrPasswordIsNull(String userName, String password) {
		Optional.ofNullable(userName)
			.orElseThrow(() -> new InvalidDataException("Username is null!"));
		Optional.ofNullable(password)
			.orElseThrow(() -> new InvalidDataException("Password is null!"));
	}
	
}
