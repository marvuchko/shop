package com.marko.shop.data.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marko.shop.data.shop.entity.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {

	Optional<ShopItem> findByName(String name);

	@Query("SELECT p.shopItem "
				+ "FROM Purchase p "
				+ "WHERE p.purchaseList.id = :id")
	List<ShopItem> findAllInPurchaseList(@Param("id") Long purchaseListId);
	
}
