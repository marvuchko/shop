package com.marko.shop.service.user.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.user.UserAuthService;
import com.marko.shop.service.user.impl.exception.UnauthorizedUserException;
import com.marko.shop.service.user.impl.exception.UnprocessableUserException;
import com.marko.shop.service.user.impl.exception.UserAlreadyExsistsException;
import com.marko.shop.service.user.impl.exception.UserNotFoundException;
import com.marko.shop.service.user.impl.util.UserValidationUtil;

@Service
@Transactional
public class DefaultUserAuthService implements UserAuthService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	public DefaultUserAuthService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User register(User user) {
		User localUser = Optional.ofNullable(user)
				.orElseThrow(() -> new UnprocessableUserException());
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(user.getUserName(), user.getPassword());
		if(userRepository.findByUserName(user.getUserName()).isPresent())
			throw new UserAlreadyExsistsException(UserAlreadyExsistsException.USER_ALREADY_EXISTS);
		localUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(localUser);
	}

	@Override
	public User logIn(String username, String password) {
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(username, password);
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UserNotFoundException());
		if (!passwordEncoder.matches(password, user.getPassword())) 
			throw new UnauthorizedUserException();
		return user;
	}

}
