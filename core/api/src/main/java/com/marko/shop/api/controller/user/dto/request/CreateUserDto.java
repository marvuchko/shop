package com.marko.shop.api.controller.user.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserDto {

	@NotNull
	@Size(min = 1, max = 120)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 120)
	private String lastName;
	
	@NotNull
	@Size(min = 1, max = 120)
	private String userName;
	
	@NotNull
	private String password;
	
	@Size(min = 1)
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
