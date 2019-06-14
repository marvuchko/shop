package com.marko.shop.data.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);
	
}
