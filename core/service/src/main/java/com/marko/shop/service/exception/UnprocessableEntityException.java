package com.marko.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends AbstractHttpException {

	private static final long serialVersionUID = 8951891413889164809L;
	
	public UnprocessableEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnprocessableEntityException(String message) {
		super(message);
	}

	public UnprocessableEntityException(Throwable cause) {
		super(cause);
	}

}
