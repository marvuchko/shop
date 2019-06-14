package com.marko.shop.data.user.repository;

import com.marko.shop.data.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
