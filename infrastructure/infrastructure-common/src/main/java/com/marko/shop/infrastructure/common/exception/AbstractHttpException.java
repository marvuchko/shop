package com.marko.shop.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractHttpException extends RuntimeException {

	private static final long serialVersionUID = -6500302042324583894L;

	public AbstractHttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractHttpException(String message) {
		super(message);
	}

	public AbstractHttpException(Throwable cause) {
		super(cause);
	}

	public HttpStatus getHttpStatus() {
		ResponseStatus responseStatus = getClass().getAnnotation(ResponseStatus.class);
		if (responseStatus == null)
			return null;
		return responseStatus.value();
	}

}
