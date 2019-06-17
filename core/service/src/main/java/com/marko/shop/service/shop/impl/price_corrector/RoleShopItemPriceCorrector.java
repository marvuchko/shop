package com.marko.shop.service.shop.impl.price_corrector;

import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.shop.entity.ShopItemType;
import com.marko.shop.data.user.entity.User;

public class RoleShopItemPriceCorrector extends ShopItemPriceCorrector {

	@Override
	protected void performCalculation(ItemWithUserAdapter itemWithUserAdapter) {
		if (itemWithUserAdapter == null) return;
		ShopItem shopItem = itemWithUserAdapter.getShopItem();
		if (shopItem == null || shopItem.getType().equals(ShopItemType.GROCERIES))
			return;
		User user = itemWithUserAdapter.getUser();
		final float roleDiscountPercentage = user.getRoles().stream()
				.map(role -> role.getDiscountByRole()).max(Float::compare).get();
		float newPrice = (float) (shopItem
				.getPrice().floatValue() * (INITIAL_PRICE_PERCENTAGE - roleDiscountPercentage) / 100.0);
		shopItem.setPrice(newPrice);
	}

}
