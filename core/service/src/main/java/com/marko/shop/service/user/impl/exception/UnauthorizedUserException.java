package com.marko.shop.service.user.impl.exception;

import com.marko.shop.common.exception.AbstractHttpRuntimeException;

public class UnauthorizedUserException extends AbstractHttpRuntimeException {

	private static final long serialVersionUID = -4748347992620094414L;
	
	public static final String UNAUTHORIZED_USER = "Unauthorized user!";
	
	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedUserException(String message) {
		super(message);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause);
	}
	
	public UnauthorizedUserException() {
		super(UNAUTHORIZED_USER);
	}

}
