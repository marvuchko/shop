package com.marko.shop.data.user.repository;

import com.marko.shop.data.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
