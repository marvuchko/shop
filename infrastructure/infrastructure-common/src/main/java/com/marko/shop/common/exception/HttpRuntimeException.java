package com.marko.shop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6500302042324583894L;

	public HttpRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpRuntimeException(String message) {
		super(message);
	}

	public HttpRuntimeException(Throwable cause) {
		super(cause);
	}

	public HttpStatus getHttpStatus() {
		ResponseStatus responseStatus = getClass().getAnnotation(ResponseStatus.class);
		if (responseStatus == null)
			return null;
		return responseStatus.value();
	}

}
