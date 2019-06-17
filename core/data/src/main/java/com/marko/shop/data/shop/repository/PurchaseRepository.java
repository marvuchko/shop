package com.marko.shop.data.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
