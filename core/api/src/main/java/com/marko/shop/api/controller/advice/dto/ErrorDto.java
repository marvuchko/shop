package com.marko.shop.api.controller.advice.dto;

import java.util.List;

public class ErrorDto {

	private int httpStatus;
	
	private List<String> messages;
	
	private String path;

	public ErrorDto() {
		super();
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
