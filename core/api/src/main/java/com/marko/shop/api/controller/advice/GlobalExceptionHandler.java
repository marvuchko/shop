package com.marko.shop.api.controller.advice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marko.shop.api.controller.advice.dto.ErrorDto;
import com.marko.shop.common.exception.AbstractHttpRuntimeException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AbstractHttpRuntimeException.class)
	public ResponseEntity<ErrorDto> handleHttpRuntimeException(
			AbstractHttpRuntimeException ex, HttpServletRequest httpServletRequest
	) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setHttpStatus(ex.getHttpStatus().value());
		errorDto.setMessages(Arrays.asList(ex.getMessage()));
		errorDto.setPath(httpServletRequest.getRequestURL().toString());
		return new ResponseEntity<>(errorDto, ex.getHttpStatus());
	}
	
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request
	) {
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> messages = fieldErrors.stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		String requestPath = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		ErrorDto errorDto = new ErrorDto();
		errorDto.setHttpStatus(status.value());
		errorDto.setMessages(messages);
		errorDto.setPath(requestPath);
	    return new ResponseEntity<>(messages.isEmpty() ? ex : errorDto, headers, status);
	}

}
