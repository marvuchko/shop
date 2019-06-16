package com.marko.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends AbstractHttpException {

	private static final long serialVersionUID = 8695835123467456017L;
	
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
	}

}
