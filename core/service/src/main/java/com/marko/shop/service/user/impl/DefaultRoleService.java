package com.marko.shop.service.user.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.shop.data.user.entity.Role;
import com.marko.shop.data.user.repository.RoleRepository;
import com.marko.shop.service.exception.EntityAlreadyExsistsException;
import com.marko.shop.service.exception.UnprocessableEntityException;
import com.marko.shop.service.user.RoleService;

@Service
public class DefaultRoleService implements RoleService {

	private final RoleRepository roleRepository;
	
	public DefaultRoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Page<Role> findAll(int page, int size) {
		if (page < 0 || size <= 0)
			return new PageImpl<>(new ArrayList<>());
		return roleRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	@Transactional
	public Role create(Role role) {
		Optional.ofNullable(role)
			.orElseThrow(() -> new UnprocessableEntityException("Role is null!"));
		if(roleRepository.findByCaption(role.getCaption()).isPresent()) 
			throw new EntityAlreadyExsistsException("Role with specified caption already exsists!");
		return roleRepository.save(role);
	}

}
