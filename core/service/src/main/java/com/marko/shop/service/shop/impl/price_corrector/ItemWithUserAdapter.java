package com.marko.shop.service.shop.impl.price_corrector;

import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;

public class ItemWithUserAdapter implements AbstractVisitorElement {

	private final ShopItem shopItem;
	
	private final User user;
	
	public ItemWithUserAdapter(ShopItem shopItem, User user) {
		this.shopItem = shopItem;
		this.user = user;
	}
	
	@Override
	public void acceptVisitor(AbstractVisitor visitor) {
		visitor.visit(this);
	}
	
	public ShopItem getShopItem() {
		return shopItem;
	}

	public User getUser() {
		return user;
	}

}
