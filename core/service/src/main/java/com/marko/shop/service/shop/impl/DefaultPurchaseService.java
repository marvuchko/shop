package com.marko.shop.service.shop.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.shop.data.shop.entity.Purchase;
import com.marko.shop.data.shop.entity.PurchaseList;
import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.shop.repository.PurchaseListRepository;
import com.marko.shop.data.shop.repository.ShopItemRepository;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.exception.EntityNotFoundException;
import com.marko.shop.service.exception.InvalidDataException;
import com.marko.shop.service.shop.PriceManager;
import com.marko.shop.service.shop.PurchaseService;
import com.marko.shop.service.shop.impl.price_corrector.ItemWithUserAdapter;

@Service
public class DefaultPurchaseService implements PurchaseService {
	
	private final PurchaseListRepository purchaseListRepository;
	
	private final UserRepository userRepository;
	
	private final ShopItemRepository shopItemRepository;
	
	private final PriceManager priceManager;
	
	public DefaultPurchaseService(
			PurchaseListRepository purchaseListRepository,
			UserRepository userRepository,
			ShopItemRepository shopItemRepository,
			PriceManager priceManager
	) {
		this.purchaseListRepository = purchaseListRepository;
		this.userRepository = userRepository;
		this.shopItemRepository = shopItemRepository;
		this.priceManager = priceManager;
	}

	@Override
	public Page<PurchaseList> findAllPurchaseListsForUser(int page, int size, Long userId) {
		if (page < 0 || size <= 0) 
			return new PageImpl<>(new ArrayList<>());
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Invalid userId!"));
		return purchaseListRepository.findAllByUser(user, PageRequest.of(page, size));
	}

	@Override
	@Transactional(readOnly = true)
	public PurchaseList purchaseItems(List<Long> itemIds, Long userId) {
		Optional.ofNullable(itemIds)
			.orElseThrow(() -> new InvalidDataException("Shop itemIds cannot be null!"));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User cannot be found!"));
		List<ShopItem> shopItems = shopItemRepository.findAllById(itemIds);
		if (shopItems.isEmpty())
			throw new EntityNotFoundException("No shop items found for given ids.");
		float totalPrice = 0;
		List<Purchase> purchases = new ArrayList<>();
		PurchaseList purchaseList = new PurchaseList();
		for(ShopItem item : shopItems) {
			priceManager.apply(new ItemWithUserAdapter(item, user));
			Purchase purchase = new Purchase();
			purchase.setShopItem(item);
			purchase.setPurchaseList(purchaseList);
			priceManager.apply(new ItemWithUserAdapter(item, user));
			totalPrice += item.getPrice();
			purchases.add(purchase);
		}
		purchaseList.setTotalPrice(totalPrice);
		purchaseList.setPurchases(purchases);
		purchaseList.setUser(user);
		return purchaseListRepository.save(purchaseList);
	}

}
