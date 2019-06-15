package com.marko.shop.service.user.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpRuntimeException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableUserException extends AbstractHttpRuntimeException {

	private static final long serialVersionUID = 8951891413889164809L;
	
	public static final String UNPROCESSABLE_USER_DATA = "Unprocessable user data!";
	
	public static final String USERNAME_NOT_PROVIDED = "Username not provided!";
	
	public UnprocessableUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnprocessableUserException(String message) {
		super(message);
	}

	public UnprocessableUserException(Throwable cause) {
		super(cause);
	}
	
	public UnprocessableUserException() {
		super(UNPROCESSABLE_USER_DATA);
	}

}
