package com.marko.shop.service.exception;

import com.marko.shop.infrastructure.common.exception.AbstractHttpException;

public class UnauthorizedUserException extends AbstractHttpException {

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
