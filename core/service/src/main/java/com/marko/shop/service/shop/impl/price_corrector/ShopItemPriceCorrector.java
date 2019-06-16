package com.marko.shop.service.shop.impl.price_corrector;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;

public abstract class ShopItemPriceCorrector implements AbstractVisitor {
	
	protected final float INITIAL_PRICE_PERCENTAGE = (float) 100.0;

	@Override
	public void visit(AbstractVisitorElement item) {
		performCalculation((ItemWithUserAdapter) item);
	}
	
	protected abstract void performCalculation(ItemWithUserAdapter itemWithUserAdapter);
	
}
