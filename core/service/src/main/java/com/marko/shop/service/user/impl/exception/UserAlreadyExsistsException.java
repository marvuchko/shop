package com.marko.shop.service.user.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpRuntimeException;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class UserAlreadyExsistsException extends AbstractHttpRuntimeException {
	
	private static final long serialVersionUID = 8589029253003354627L;
	
	public static final String USER_ALREADY_EXISTS = "User already exsists!";
	
	public UserAlreadyExsistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExsistsException(String message) {
		super(message);
	}

	public UserAlreadyExsistsException(Throwable cause) {
		super(cause);
	}
	
	public UserAlreadyExsistsException() {
		super(USER_ALREADY_EXISTS);
	}

}
