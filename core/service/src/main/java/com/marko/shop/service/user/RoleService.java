package com.marko.shop.service.user;

import com.marko.shop.data.user.entity.Role;
import org.springframework.data.domain.Page;

public interface RoleService {

	Page<Role> findAll(int page, int size);

	Role create(Role role);
	
}
