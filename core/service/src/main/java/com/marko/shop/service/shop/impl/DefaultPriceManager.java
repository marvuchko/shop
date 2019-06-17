package com.marko.shop.service.shop.impl;

import java.util.List;

import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitorElement;
import com.marko.shop.service.shop.PriceManager;

public class DefaultPriceManager implements PriceManager {

	private final AbstractVisitor defaultPriceCorrector;

	private final List<AbstractVisitor> percentagepriceCorrectors;

	public DefaultPriceManager(AbstractVisitor defaultPriceCorrector,  List<AbstractVisitor> percentagepriceCorrectors) {
		this.defaultPriceCorrector = defaultPriceCorrector;
		this.percentagepriceCorrectors = percentagepriceCorrectors;
	}
	
	@Override
	public void apply(AbstractVisitorElement element) {
		element.acceptVisitor(defaultPriceCorrector);
		Float price = lowestPercentagePrice(element);
		element.setValue(price);
	}

	private Float lowestPercentagePrice(AbstractVisitorElement element) {
		Float lowestPrice = (Float) element.getValue();
		for(AbstractVisitor percentageCorrector : percentagepriceCorrectors) {
			Float oldPrice = (Float) element.getValue();
			element.acceptVisitor(percentageCorrector);
			Float newPrice = (Float) element.getValue();
			if(newPrice.floatValue() < oldPrice.floatValue())
				lowestPrice = newPrice;
			element.setValue(oldPrice);
		}
		return  lowestPrice;
	}
}
