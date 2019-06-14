package com.marko.shop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractHttpRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6500302042324583894L;

	public AbstractHttpRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractHttpRuntimeException(String message) {
		super(message);
	}

	public AbstractHttpRuntimeException(Throwable cause) {
		super(cause);
	}

	public HttpStatus getHttpStatus() {
		ResponseStatus responseStatus = getClass().getAnnotation(ResponseStatus.class);
		if (responseStatus == null)
			return null;
		return responseStatus.value();
	}

}
