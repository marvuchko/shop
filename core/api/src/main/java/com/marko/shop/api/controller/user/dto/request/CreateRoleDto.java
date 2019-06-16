package com.marko.shop.api.controller.user.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Transfers data for user role createion.")
public class CreateRoleDto {

	@NotNull
	@Size(min = 1, max = 120)
	private String caption;
	
	@NotNull
	@Size(min = 1)
	private String description;

	public CreateRoleDto() {
		super();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
