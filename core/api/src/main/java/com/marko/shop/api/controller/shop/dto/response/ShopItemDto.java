package com.marko.shop.api.controller.shop.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Transfers the data about shop item.")
public class ShopItemDto {

	@ApiModelProperty("Id of the shop item.")
	private Long id;
	
	@ApiModelProperty("Name of the shop item. Must be unique.")
	private String name;
	
	@ApiModelProperty("Detailed description of the shop item.")
	private String description;
	
	@ApiModelProperty("Price of the shop item.")
	private Float price;
	
	@ApiModelProperty("Image url of the shop item.")
	private String imageUrl;
	
	@ApiModelProperty("The date shop item was created.")
	private Date createdAt;
	
	@ApiModelProperty("The date shop item was updated.")
	private Date updatedAt;

	public ShopItemDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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
