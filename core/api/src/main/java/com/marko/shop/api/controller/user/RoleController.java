package com.marko.shop.api.controller.user;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marko.shop.api.controller.user.dto.request.CreateRoleDto;
import com.marko.shop.api.controller.user.dto.response.RoleDto;
import com.marko.shop.api.converter.DataConverter;
import com.marko.shop.data.user.entity.Role;
import com.marko.shop.service.user.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/role")
@Api(tags = "Role Controller")
@SuppressWarnings("unchecked")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
					  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Fetches all user roles with pagination from the database.")
	public Page<RoleDto> getRoles(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size
	) {
		return (Page<RoleDto>) DataConverter.convertToPage(roleService.findAll(page, size), RoleDto.class);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Creates new user role if not exsist. Only ADMIN can do this.")
	public RoleDto createRole(@Valid @RequestBody CreateRoleDto dto) {
		Role role = roleService.create((Role) DataConverter.convert(dto, Role.class));
		return (RoleDto) DataConverter.convert(role, RoleDto.class);
	}
	
}
