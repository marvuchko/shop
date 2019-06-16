package com.marko.shop.api.controller.shop.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Transfers data about shop item creation.")
public class CreateShopItem {
	
	@NotNull
	@Size(min = 1, max = 120)
	@ApiModelProperty(value = "Name of the shop item. Must be unique.", required = true)
	private String name;
	
	@Size(min = 1)
	@ApiModelProperty("Detailed description of the shop item.")
	private String description;
	
	@NotNull
	@ApiModelProperty(value = "Price of the shop item.", required = true)
	private Float price;
	
	@Size(min = 1)
	@ApiModelProperty("Image url of the shop item.")
	private String imageUrl;

	public CreateShopItem() {
		super();
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

}
