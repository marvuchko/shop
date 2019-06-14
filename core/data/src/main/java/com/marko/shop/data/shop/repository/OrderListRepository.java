package com.marko.shop.data.shop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.PurchaseList;

public interface OrderListRepository extends JpaRepository<PurchaseList, UUID> {
}
