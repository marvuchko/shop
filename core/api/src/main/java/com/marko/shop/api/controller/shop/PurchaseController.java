package com.marko.shop.api.controller.shop;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marko.shop.api.controller.shop.dto.response.PurchaseListDto;
import com.marko.shop.api.controller.shop.dto.response.ShopItemDto;
import com.marko.shop.api.converter.DataConverter;
import com.marko.shop.data.shop.entity.PurchaseList;
import com.marko.shop.infrastructure.security.AuthenticatedUser;
import com.marko.shop.service.shop.PurchaseService;
import com.marko.shop.service.shop.ShopItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shop/purchase")
@Api(tags = "Purchase Controller")
public class PurchaseController {
	
	private final PurchaseService purchaseService;
	
	private final ShopItemService shopItemService;

	public PurchaseController(
			PurchaseService purchaseService,
			ShopItemService shopItemService
	) {
		this.purchaseService = purchaseService;
		this.shopItemService = shopItemService;
	}
	
	@PostMapping
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Allows current authenticated user to purchase items.")
	public PurchaseListDto purchase(
			@Valid @RequestBody @NotNull @Size(min = 1) List<Long> itemIds
	) {
		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PurchaseList list = purchaseService.purchaseItems(itemIds, authUser.getUserId());
		return convertPurchaseListToDto(list);
	}

	@GetMapping("/my-purchases")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Fetches all purchases for the current authenticated user.")
	public Page<PurchaseListDto> getMyPurchases(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size
	) {
		AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Page<PurchaseList> lists = purchaseService.findAllPurchaseListsForUser(page, size, authUser.getUserId());
		return lists.map(item -> convertPurchaseListToDto(item));
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('EMPLOYEE') || hasRole('ADMIN')")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Fetches all purchases for the user. Only ADMIN and EMPLOYEE can do this.")
	public Page<PurchaseListDto> getPurchasesForUser(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
			@PathVariable("id") Long id
	) {
		Page<PurchaseList> lists = purchaseService.findAllPurchaseListsForUser(page, size, id);
		return lists.map(item -> convertPurchaseListToDto(item));
	}
	
	@SuppressWarnings("unchecked")
	private PurchaseListDto convertPurchaseListToDto(PurchaseList list) {
		PurchaseListDto dto =(PurchaseListDto) DataConverter.convert(list, PurchaseListDto.class);
		dto.setShopItems((List<ShopItemDto>) DataConverter
				.convertToList(shopItemService.findAllInPurchaseList(list.getId()), ShopItemDto.class));
		return dto;
	}
	
}
