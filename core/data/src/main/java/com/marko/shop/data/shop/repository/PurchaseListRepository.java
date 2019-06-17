package com.marko.shop.data.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.shop.entity.PurchaseList;
import com.marko.shop.data.user.entity.User;

public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long> {

	Page<PurchaseList> findAllByUser(User user, Pageable pageable);
	
}
