package com.marko.shop.service.user;

import org.springframework.data.domain.Page;

import com.marko.shop.data.user.entity.Role;

public interface RoleService {

	Page<Role> findAll(int page, int size);

	Role create(Role role);
	
}
