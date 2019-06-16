package com.marko.shop.service.shop;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;

public interface PriceManager {

	public void apply(AbstractVisitorElement element);
	
}
