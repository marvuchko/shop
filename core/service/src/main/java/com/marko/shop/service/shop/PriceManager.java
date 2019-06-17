package com.marko.shop.service.shop;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;

public interface PriceManager {

	void apply(AbstractVisitorElement element);
	
}
