package com.marko.shop.api.controller.shop;

import javax.validation.Valid;

import com.marko.shop.data.shop.entity.ShopItemType;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marko.shop.api.controller.shop.dto.request.CreateShopItem;
import com.marko.shop.api.controller.shop.dto.response.ShopItemDto;
import com.marko.shop.api.converter.DataConverter;
import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.infrastructure.security.AuthenticatedUser;
import com.marko.shop.service.shop.ShopItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shop/item")
@Api(tags = "Shop Item Controller")
@SuppressWarnings("unchecked")
public class ShopItemController {
	
	private final ShopItemService shopItemService;

	public ShopItemController(ShopItemService shopItemService) {
		this.shopItemService = shopItemService;
	}
	
	@GetMapping
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Fetches all shop items from the database with pagination.")
	public Page<ShopItemDto> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size
	) {
		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return (Page<ShopItemDto>) DataConverter
				.convertToPage(shopItemService.findAll(page, size, authUser.getUserId()), ShopItemDto.class);
	}
	
	@PostMapping("/groceries")
	@PreAuthorize("hasRole('EMPLOYEE') || hasRole('ADMIN')")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Create groceries shop item. Only ADMIN and EMPLOYEE can do this.")
	public ShopItemDto createGroceries(
			@Valid @RequestBody CreateShopItem dto
	) {
		ShopItem shopItem = shopItemService.create((ShopItem) DataConverter.convert(dto, ShopItem.class), ShopItemType.GROCERIES);
		return (ShopItemDto) DataConverter.convert(shopItem, ShopItemDto.class);
	}

	@PostMapping("/other")
	@PreAuthorize("hasRole('EMPLOYEE') || hasRole('ADMIN')")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
			allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Create groceries shop item. Only ADMIN and EMPLOYEE can do this.")
	public ShopItemDto createOtherItems(
			@Valid @RequestBody CreateShopItem dto
	) {
		ShopItem shopItem = shopItemService.create((ShopItem) DataConverter.convert(dto, ShopItem.class), ShopItemType.OTHER);
		return (ShopItemDto) DataConverter.convert(shopItem, ShopItemDto.class);
	}
	
}
