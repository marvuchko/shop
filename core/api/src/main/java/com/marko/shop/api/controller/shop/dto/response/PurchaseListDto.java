package com.marko.shop.api.controller.shop.dto.response;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Transfers data about list of purchased items.")
public class PurchaseListDto {

	@ApiModelProperty("Id of the list.")
	private Long id;
	
	@ApiModelProperty("Total price of all purchased items.")
	private Float totalPrice;
	
	@ApiModelProperty("Date when list is created.")
	private Date createdAt;
	
	@ApiModelProperty("Date when list is updated.")
	private Date updatedAt;

	@ApiModelProperty("Purchased items.")
	private List<ShopItemDto> shopItems;

	public PurchaseListDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
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

	public List<ShopItemDto> getShopItems() {
		return shopItems;
	}

	public void setShopItems(List<ShopItemDto> shopItems) {
		this.shopItems = shopItems;
	}
	
}
