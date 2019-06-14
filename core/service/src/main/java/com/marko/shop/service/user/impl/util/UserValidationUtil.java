package com.marko.shop.service.user.impl.util;

import java.util.Optional;

import com.marko.shop.service.user.impl.exception.InvalidUserDataException;

public class UserValidationUtil {

	private UserValidationUtil() {
		super();
	}

	public static void checkIfUsernameOrPasswordIsNull(String userName, String password) {
		Optional.ofNullable(userName)
			.orElseThrow(() -> new InvalidUserDataException(InvalidUserDataException.USERNAME_IS_NULL));
		Optional.ofNullable(password)
			.orElseThrow(() -> new InvalidUserDataException(InvalidUserDataException.PASSWORD_IS_NULL));
	}
	
}
