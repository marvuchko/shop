package com.marko.shop.data.shop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, UUID> {
}
