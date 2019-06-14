package com.marko.shop.data.shop.repository;

import com.marko.shop.data.shop.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {
}
