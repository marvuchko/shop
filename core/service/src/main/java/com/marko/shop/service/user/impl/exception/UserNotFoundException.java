package com.marko.shop.service.user.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.common.exception.AbstractHttpRuntimeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends AbstractHttpRuntimeException {

	private static final long serialVersionUID = -851178732290099214L;
	
	private static final String USER_NOT_FOUND = "User not found!";
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}

}
