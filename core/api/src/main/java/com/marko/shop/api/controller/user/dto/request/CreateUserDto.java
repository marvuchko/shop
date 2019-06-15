package com.marko.shop.api.controller.user.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Transfers data for user creation.")
public class CreateUserDto {

	@NotNull
	@Size(min = 1, max = 120)
	@ApiModelProperty(value = "First name of the user.", required = true)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 120)
	@ApiModelProperty(value = "Last name of the user.", required = true)
	private String lastName;
	
	@NotNull
	@Size(min = 1, max = 120)
	@ApiModelProperty(value = "Username of the user.", required = true)
	private String userName;
	
	@NotNull
	@ApiModelProperty(value = "Password of the user.", required = true)
	private String password;
	
	@Size(min = 1)
	@ApiModelProperty("Image URL of the user.")
	private String imageUrl;

	public CreateUserDto() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
