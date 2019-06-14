package com.marko.shop.data.shop.repository;

import com.marko.shop.data.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
