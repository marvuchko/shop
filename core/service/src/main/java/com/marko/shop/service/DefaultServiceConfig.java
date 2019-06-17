package com.marko.shop.service;

import java.util.Arrays;
import java.util.List;

import com.marko.shop.service.shop.impl.price_corrector.BillShopItemPriceCorrector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.marko.shop.infrastructure.common.util.DateUtil;
import com.marko.shop.infrastructure.common.visitor.AbstractVisitor;
import com.marko.shop.service.shop.PriceManager;
import com.marko.shop.service.shop.impl.DefaultPriceManager;
import com.marko.shop.service.shop.impl.price_corrector.CreationDateShopItemPriceCorrector;
import com.marko.shop.service.shop.impl.price_corrector.RoleShopItemPriceCorrector;

@Configuration
@ComponentScan(basePackageClasses = {
		ServicePackage.class
})
public class DefaultServiceConfig {
	
	@Value("${app.creation-date-discount-percentage}")
	private Float creationDateDiscountPercentage;
	
	@Value("${app.discount-last-days}")
	private Integer discountLastDays;

	@Value("${app.bill-discount}")
	private Float billDiscount;

	@Value("${app.per-money-amount}")
	private Float perMoneyAmmount;

	@Bean
	public PriceManager priceManagerBean() {
		List<AbstractVisitor> correctors = Arrays.asList(
				new RoleShopItemPriceCorrector(),
				new CreationDateShopItemPriceCorrector(
						creationDateDiscountPercentage,
						DateUtil.addDays(-discountLastDays)
				)
		);
		AbstractVisitor defaultCorrector = new BillShopItemPriceCorrector(billDiscount, perMoneyAmmount);
		return new DefaultPriceManager(defaultCorrector, correctors);
	}
	
}
