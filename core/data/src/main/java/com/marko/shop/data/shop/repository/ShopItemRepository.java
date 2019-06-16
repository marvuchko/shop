package com.marko.shop.data.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {

	Optional<ShopItem> findByName(String name);
}
