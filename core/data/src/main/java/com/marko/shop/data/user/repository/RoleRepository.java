package com.marko.shop.data.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.shop.data.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
