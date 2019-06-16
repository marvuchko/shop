package com.marko.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpException;

@ResponseStatus(HttpStatus.FOUND)
public class EntityAlreadyExsistsException extends AbstractHttpException {
	
	private static final long serialVersionUID = 8589029253003354627L;
	
	public EntityAlreadyExsistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExsistsException(String message) {
		super(message);
	}

	public EntityAlreadyExsistsException(Throwable cause) {
		super(cause);
	}

}
