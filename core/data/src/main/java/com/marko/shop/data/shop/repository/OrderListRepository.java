package com.marko.shop.data.shop.repository;

import com.marko.shop.data.shop.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
