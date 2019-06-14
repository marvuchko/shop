package com.marko.shop.api.controller.user;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marko.shop.api.controller.user.converter.UserAuthConverter;
import com.marko.shop.api.controller.user.dto.request.CreateUserDto;
import com.marko.shop.api.controller.user.dto.response.UserAuthDto;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.service.user.UserAuthService;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {

	private final UserAuthService userAuthService;

	public UserAuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}
	
	@GetMapping("/login")
	public UserAuthDto logIn(
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password
	) {
		return UserAuthConverter.toDto(userAuthService.logIn(userName, password));
	}
	
	@PostMapping("/register")
	public UserAuthDto register(
			@Valid @RequestBody CreateUserDto dto
	) {
		User user = userAuthService.register(UserAuthConverter.toEntity(dto));
		return UserAuthConverter.toDto(userAuthService.register(user));
	}
	
}
