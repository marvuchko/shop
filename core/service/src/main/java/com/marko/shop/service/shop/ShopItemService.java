package com.marko.shop.service.shop;

import org.springframework.data.domain.Page;

import com.marko.shop.data.shop.entity.ShopItem;

public interface ShopItemService {

	/**
	 * Fetches all shop items with pagination
	 * 
	 * @param page number of the current page
	 * @param size size of the page
	 * 
	 * @param userId id of the user that fetches the items
	 * 
	 * @return {@linkplain Page} of {@linkplain ShopItem}
	 */
	Page<ShopItem> findAll(int page, int size, Long userId);
	
	/**
	 * Creates new shop item.
	 * 
	 * @param shopItem shop item that needs to be created.
	 * 
	 * @return new {@linkplain ShopItem}
	 */
	ShopItem create(ShopItem shopItem);
	
}
