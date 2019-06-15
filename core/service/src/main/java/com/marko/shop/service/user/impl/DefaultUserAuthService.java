package com.marko.shop.service.user.impl;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marko.shop.data.user.entity.Role;
import com.marko.shop.data.user.entity.RoleType;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.RoleRepository;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.user.UserAuthService;
import com.marko.shop.service.user.impl.exception.RoleNotFoundException;
import com.marko.shop.service.user.impl.exception.UnauthorizedUserException;
import com.marko.shop.service.user.impl.exception.UnprocessableUserException;
import com.marko.shop.service.user.impl.exception.UserAlreadyExsistsException;
import com.marko.shop.service.user.impl.exception.UserNotFoundException;
import com.marko.shop.service.user.impl.util.UserValidationUtil;

@Service
@Transactional
public class DefaultUserAuthService implements UserAuthService {

	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;

	public DefaultUserAuthService(
			UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder
	) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(User user) {
		User localUser = Optional.ofNullable(user)
				.orElseThrow(() -> new UnprocessableUserException());
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(user.getUserName(), user.getPassword());
		if(userRepository.findByUserName(user.getUserName()).isPresent())
			throw new UserAlreadyExsistsException();
		localUser.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByCaption(RoleType.USER.getType())
			.orElseThrow(() -> new RoleNotFoundException());
		localUser.setRoles(Arrays.asList(role));
		return userRepository.save(localUser);
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User registerEmployee(User employee) {
		User localEmployee = Optional.ofNullable(employee)
				.orElseThrow(() -> new UnprocessableUserException());
		UserValidationUtil.checkIfUsernameOrPasswordIsNull(employee.getUserName(), employee.getPassword());
		if(userRepository.findByUserName(employee.getUserName()).isPresent())
			throw new UserAlreadyExsistsException();
		localEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
		Role role = roleRepository.findByCaption(RoleType.EMPLOYEE.getType())
			.orElseThrow(() -> new RoleNotFoundException());
		localEmployee.setRoles(Arrays.asList(role));
		return userRepository.save(localEmployee);
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
