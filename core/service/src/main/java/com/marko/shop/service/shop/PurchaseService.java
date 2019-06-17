package com.marko.shop.service.shop;

import java.util.List;

import org.springframework.data.domain.Page;

import com.marko.shop.data.shop.entity.PurchaseList;

public interface PurchaseService {

	Page<PurchaseList> findAllPurchaseListsForUser(int page, int size, Long userId);
	
	PurchaseList purchaseItems(List<Long> itemIds, Long userId);
	
}
