package com.marko.shop.api.controller.user.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Returns the data about the authentified user.")
public class UserAuthDto {

    @ApiModelProperty("Id of the authentified user.")
	private Long id;
	
    @ApiModelProperty("First name of the authentified user.")
	private String firstName;

    @ApiModelProperty("Last name of the authentified user.")
	private String lastName;
	
    @ApiModelProperty("Username of the authentified user.")
	private String userName;
	
    @ApiModelProperty("Encrypted password of the authentified user.")
	private String password;
	
    @ApiModelProperty("Image URL of the authentified user.")
	private String imageUrl;
    
    @ApiModelProperty("The date user has been created.")
    private Date createdAt;
    
    @ApiModelProperty("The date user has been updated.")
    private Date updatedAt;

	public UserAuthDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
