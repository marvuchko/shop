package com.marko.shop.service.user.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.shop.data.user.entity.Role;
import com.marko.shop.data.user.entity.RoleType;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.RoleRepository;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.infrastructure.security.JwtClaimConstants;
import com.marko.shop.infrastructure.security.JwtToken;
import com.marko.shop.security.jwt.JwtTokenManager;
import com.marko.shop.service.exception.EntityAlreadyExsistsException;
import com.marko.shop.service.exception.EntityNotFoundException;
import com.marko.shop.service.exception.InvalidDataException;
import com.marko.shop.service.exception.UnauthorizedUserException;
import com.marko.shop.service.exception.UnprocessableEntityException;
import com.marko.shop.service.user.UserAuthService;
import com.marko.shop.service.user.impl.util.UserJwtUtil;
import com.marko.shop.service.user.impl.util.UserValidationUtil;

import io.jsonwebtoken.Claims;

@Service
public class DefaultUserAuthService implements UserAuthService {

	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtTokenManager jwtTokenManager;

	public DefaultUserAuthService(
			UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder,
			JwtTokenManager jwtTokenManager
	) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenManager = jwtTokenManager;
	}

	@Override
	@Transactional
	public User register(User user, RoleType type) {
		User localUser = Optional.ofNullable(user)
				.orElseThrow(() -> new UnprocessableEntityException("User is null!"));
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(user.getUserName(), user.getPassword());
		Optional.ofNullable(type).orElseThrow(() -> new InvalidDataException("User type cannot be null!"));
		if(userRepository.findByUserName(user.getUserName()).isPresent())
			throw new EntityAlreadyExsistsException("User already exsits.");
		localUser.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByCaption(type.getType())
			.orElseThrow(() -> new EntityNotFoundException("Specified user role was not found!"));
		localUser.setRoles(Arrays.asList(role));
		return userRepository.save(localUser);
	}

	@Override
	public JwtToken logIn(String username, String password) {
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(username, password);
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new EntityNotFoundException("No user for the given username has been found!"));
		if (!passwordEncoder.matches(password, user.getPassword())) 
			throw new UnauthorizedUserException();
		List<String> userRoles = user.getRoles()
				.stream().map(role -> role.getCaption().toUpperCase()).collect(Collectors.toList());
		return UserJwtUtil.buildJwtToken(user, userRoles, jwtTokenManager);
	}

	@Override
	public JwtToken refreshTokens(String refreshToken) {
		Optional.ofNullable(refreshToken)
			.orElseThrow(() -> new InvalidDataException("Refresh token is null!"));
		try {
			Claims claims = (Claims) jwtTokenManager.getJwtParser().parse(refreshToken).getBody();
			Integer userId = (Integer) claims.get(JwtClaimConstants.USER_ID);
			User user = userRepository.findById(userId.longValue())
				.orElseThrow(() -> 
				new EntityNotFoundException("Refresh token provided userId doesn't belong to any user!"));
			List<String> userRoles = user.getRoles()
					.stream().map(role -> role.getCaption().toUpperCase()).collect(Collectors.toList());
			return UserJwtUtil.buildJwtToken(user, userRoles, jwtTokenManager);
		} catch (Exception ex) {
			throw new AccessDeniedException(ex.getMessage());
		}
	}

}
