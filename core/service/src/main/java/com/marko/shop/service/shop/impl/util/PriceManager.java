package com.marko.shop.service.shop.impl.util;

import java.util.List;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;

public class PriceManager {
	
	private List<AbstractVisitor> priceCorrectors;

	public PriceManager(List<AbstractVisitor> priceCorrectors) {
		this.priceCorrectors = priceCorrectors;
	}
	
	public void apply(AbstractVisitorElement element) {
		priceCorrectors.forEach(corrector -> element.acceptVisitor(corrector));
	}
}
