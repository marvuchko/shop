package com.marko.shop.service.shop.impl.util;

import java.util.Date;

import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.user.entity.User;

public class CreationDateShopItemPriceCorrector extends ShopItemPriceCorrector {
	
	private final Float creationDateDiscountPercentage;
	
	private final Date discountStartDate;

	public CreationDateShopItemPriceCorrector(
			Float creationDateDiscountPercentage,
			Date discountStartDate
	) {
		this.creationDateDiscountPercentage = creationDateDiscountPercentage;
		this.discountStartDate = discountStartDate;
	}

	@Override
	protected void performCalculation(ItemWithUserAdapter itemWithUserAdapter) {
		ShopItem shopItem = itemWithUserAdapter.getShopItem();
		User user = itemWithUserAdapter.getUser();
		final float discountPercentage = user.getCreatedAt()
				.compareTo(discountStartDate) <= 0 ? creationDateDiscountPercentage : 0;
		float newPrice = (float) (shopItem
				.getPrice().floatValue() * (INITIAL_PRICE_PERCENTAGE - discountPercentage) / 100.0);
		shopItem.setPrice(newPrice);
	}

}
