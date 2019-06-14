package com.marko.shop.service.user.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.common.exception.AbstractHttpRuntimeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserDataException extends AbstractHttpRuntimeException {

	private static final long serialVersionUID = 8695835123467456017L;
	
	public static final String USERNAME_IS_NULL = "Username is null!";

	public static final String PASSWORD_IS_NULL = "Password is null!";
	
	public InvalidUserDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserDataException(String message) {
		super(message);
	}

	public InvalidUserDataException(Throwable cause) {
		super(cause);
	}

}
