package com.marko.shop.service.shop.impl;

import java.util.List;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;
import com.marko.shop.service.shop.PriceManager;

public class DefaultPriceManager implements PriceManager {
	
	private List<AbstractVisitor> priceCorrectors;

	public DefaultPriceManager(List<AbstractVisitor> priceCorrectors) {
		this.priceCorrectors = priceCorrectors;
	}
	
	@Override
	public void apply(AbstractVisitorElement element) {
		priceCorrectors.forEach(corrector -> element.acceptVisitor(corrector));
	}
}
