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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("auth")
@Api(tags = "User Authentication Controller")
public class UserAuthController {

	private final UserAuthService userAuthService;

	public UserAuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}
	
	@GetMapping("/login")
	@ApiOperation("Logs in the user into the system.")
	public UserAuthDto logIn(
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password
	) {
		return UserAuthConverter.toDto(userAuthService.logIn(userName, password));
	}
	
	@PostMapping("/user/register")
	@ApiOperation("Registres a new user into the system.")
	public UserAuthDto registerUser(
			@Valid @RequestBody CreateUserDto dto
	) {
		User user = userAuthService.registerUser(UserAuthConverter.toEntity(dto));
		return UserAuthConverter.toDto(user);
	}
	
	@PostMapping("/employee/register")
	@ApiOperation("Registres a new employee into the system.")
	public UserAuthDto registerEmployee(
			@Valid @RequestBody CreateUserDto dto
	) {
		User employee = userAuthService.registerEmployee(UserAuthConverter.toEntity(dto));
		return UserAuthConverter.toDto(employee);
	}
	
}
