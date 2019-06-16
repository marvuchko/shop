package com.marko.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.marko.shop.infrastructure.common.util.DateUtil;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.service.shop.impl.util.CreationDateShopItemPriceCorrector;
import com.marko.shop.service.shop.impl.util.PriceManager;
import com.marko.shop.service.shop.impl.util.RoleShopItemPriceCorrector;
import com.marko.shop.service.shop.impl.util.ShopItemPriceCorrector;

@Configuration
@ComponentScan(basePackageClasses = {
		ServicePackage.class
})
public class DefaultServiceConfig {
	
	@Value("${app.creation-date-discount-percentage}")
	private Float creationDateDiscountPercentage;
	
	@Value("${app.discount-last-days}")
	private Integer discountLastDays;

	@Bean
	public PriceManager priceManagerBean() {
		ShopItemPriceCorrector rolePriceCorrector = new RoleShopItemPriceCorrector();
		ShopItemPriceCorrector datePriceCorrector = new CreationDateShopItemPriceCorrector(
				creationDateDiscountPercentage, DateUtil.addDays(-discountLastDays));
		List<AbstractVisitor> correctors = new ArrayList<>();
		correctors.add(rolePriceCorrector);
		correctors.add(datePriceCorrector);
		return new PriceManager(correctors);
	}
	
}
