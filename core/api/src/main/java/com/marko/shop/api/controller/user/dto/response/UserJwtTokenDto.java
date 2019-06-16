package com.marko.shop.api.controller.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Contains access and refresh tokens for user.")
public class UserJwtTokenDto {

	@ApiModelProperty("Access token for JWT authentication.")
	private String accessToken;
	
	@ApiModelProperty("Refresh token required for JWT authentication.")
	private String refreshToken;

	public UserJwtTokenDto() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
