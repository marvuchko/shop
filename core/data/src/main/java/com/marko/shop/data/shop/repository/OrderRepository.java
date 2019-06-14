package com.marko.shop.data.shop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.Purchase;

public interface OrderRepository extends JpaRepository<Purchase, UUID> {
}
