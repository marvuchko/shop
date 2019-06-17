package com.marko.shop.api.controller.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marko.shop.api.controller.user.dto.request.CreateUserDto;
import com.marko.shop.api.controller.user.dto.response.UserAuthDto;
import com.marko.shop.api.controller.user.dto.response.UserJwtTokenDto;
import com.marko.shop.api.converter.DataConverter;
import com.marko.shop.data.user.entity.RoleType;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.service.user.UserAuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authentication Controller")
public class AuthController {

	private final UserAuthService userAuthService;

	public AuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}
	
	@GetMapping("/login")
	@ApiOperation("Logs in the user into the system.")
	public UserJwtTokenDto logIn(
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password
	) {
		return (UserJwtTokenDto) DataConverter
				.convert(userAuthService.logIn(userName, password), UserJwtTokenDto.class);
	}
	
	@PostMapping("/user/register")
	@ApiOperation("Registres a new user into the system.")
	public UserAuthDto registerUser(
			@Valid @RequestBody CreateUserDto dto
	) {
		User user = userAuthService.register((User) DataConverter.convert(dto, User.class), RoleType.USER);
		return (UserAuthDto) DataConverter.convert(user, UserAuthDto.class);
	}
	
	@PostMapping("/employee/register")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
	  				  allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Registres a new employee into the system. Only ADMIN can do this.")
	public UserAuthDto registerEmployee(
			@Valid @RequestBody CreateUserDto dto
	) {
		User employee = userAuthService.register((User) DataConverter.convert(dto, User.class), RoleType.EMPLOYEE);
		return (UserAuthDto) DataConverter.convert(employee, UserAuthDto.class);
	}
	
	@PostMapping("/token/refresh")
	public UserJwtTokenDto refreshTokens(
			@Valid @RequestBody @NotNull String refreshToken
	) {
		return (UserJwtTokenDto) DataConverter
				.convert(userAuthService.refreshTokens(refreshToken), UserJwtTokenDto.class);
	}
	
}
