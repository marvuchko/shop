package com.marko.shop.api.controller.user.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Transfers data about user roles.")
public class RoleDto {
	
	@ApiModelProperty("Id of the role.")
	private Long id;
	
	@ApiModelProperty("Caption of the role.")
	private String caption;

	@ApiModelProperty("Description of the role.")
	private String description;
	
	@ApiModelProperty("The date role was created.")
	private Date createdAt;
	
	@ApiModelProperty("The date role was updated.")
	private Date updatedAt;

	public RoleDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
