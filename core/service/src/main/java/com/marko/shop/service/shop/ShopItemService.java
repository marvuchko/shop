package com.marko.shop.service.shop;

import java.util.List;

import com.marko.shop.data.shop.entity.ShopItemType;
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
	 * @param type type of the shop item.
	 * 
	 * @return new {@linkplain ShopItem}
	 */
	ShopItem create(ShopItem shopItem, ShopItemType type);
	
	/**
	 * Fetches all shop items for a specified purchase list
	 * 
	 * @param purchaseListId id of the list
	 * 
	 * @return list of {@linkplain ShopItem}
	 */
	List<ShopItem> findAllInPurchaseList(Long purchaseListId);
	
}
