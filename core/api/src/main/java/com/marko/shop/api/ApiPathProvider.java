package com.marko.shop.api;

import springfox.documentation.spring.web.paths.AbstractPathProvider;

public class ApiPathProvider extends AbstractPathProvider {

	public static final String ROOT = "/";
	
	private final String contextPath;

	public ApiPathProvider(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	protected String applicationPath() {
		return contextPath == null ? ROOT : contextPath;
	}

	@Override
	protected String getDocumentationPath() {
		return "/admin";
	}

}
