package com.marko.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends AbstractHttpException {

	private static final long serialVersionUID = -851178732290099214L;
	
	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

}
