package com.marko.shop.service.shop.impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.shop.repository.ShopItemRepository;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.exception.EntityAlreadyExsistsException;
import com.marko.shop.service.exception.EntityNotFoundException;
import com.marko.shop.service.exception.UnprocessableEntityException;
import com.marko.shop.service.shop.ShopItemService;
import com.marko.shop.service.shop.impl.price_corrector.ItemWithUserAdapter;

@Service
public class DefaultShopItemService implements ShopItemService {

	private final ShopItemRepository shopItemRepository;
	
	private final UserRepository userRepository;	
	
	private final PriceManager priceManager;
	
	public DefaultShopItemService(
			ShopItemRepository shopItemRepository,
			UserRepository userRepository,
			PriceManager priceManager
	) {
		this.shopItemRepository = shopItemRepository;
		this.userRepository = userRepository;
		this.priceManager = priceManager;
	}

	@Override
	public Page<ShopItem> findAll(int page, int size, Long userId) {
		if (page < 0 || size <= 0)
			return new PageImpl<>(new ArrayList<>());
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User for authenticated userId not found!"));
		Page<ShopItem> shopItems = shopItemRepository.findAll(PageRequest.of(page, size));
		shopItems.forEach(item -> priceManager.apply(new ItemWithUserAdapter(item, user)));
		return shopItems;
	}

	@Override
	@Transactional
	public ShopItem create(ShopItem shopItem) {
		Optional.ofNullable(shopItem)
			.orElseThrow(() -> new UnprocessableEntityException("Shop item is null!"));
		Optional.ofNullable(shopItem.getName())
			.orElseThrow(() -> new UnprocessableEntityException("Shop item name must be not null!"));
		if(shopItemRepository.findByName(shopItem.getName()).isPresent())
			throw new EntityAlreadyExsistsException("Specified shop item already exsists!");
		return shopItemRepository.save(shopItem);
	}
 
}
