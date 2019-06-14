package com.marko.shop.api.controller.user.converter;

import org.modelmapper.ModelMapper;

import com.marko.shop.api.controller.user.dto.request.CreateUserDto;
import com.marko.shop.api.controller.user.dto.response.UserAuthDto;
import com.marko.shop.data.user.entity.User;

public class UserAuthConverter {

	private UserAuthConverter() {
		super();
	}

	public static UserAuthDto toDto(User user) {
		if (user == null)
			return new UserAuthDto();
		return new ModelMapper().map(user, UserAuthDto.class);
	}

	public static User toEntity(CreateUserDto dto) {
		if (dto == null)
			return null;
		return new ModelMapper().map(dto, User.class);
	}

}
