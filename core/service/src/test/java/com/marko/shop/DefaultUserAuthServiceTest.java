package com.marko.shop;

import com.marko.shop.data.user.entity.RoleType;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.RoleRepository;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.security.jwt.JwtTokenManager;
import com.marko.shop.service.exception.*;
import com.marko.shop.service.user.impl.DefaultUserAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserAuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenManager jwtTokenManager;

    @InjectMocks
    private DefaultUserAuthService service;

    @Test(expected = InvalidDataException.class)
    public void logInShouldThrowInvalidDataExceptionIfNoParametersProvided() {
        service.logIn(null, null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void logInShouldThrowEntityNotFoundExceptionWhenNoUserIsFound() {
        when(userRepository.findByUserName("marko")).thenReturn(Optional.empty());
        service.logIn("marko", "123");
    }

    @Test(expected = UnauthorizedUserException.class)
    public void logInShouldThrowUnauthorizedUserExceptionWhenPasswordsDontMatch() {
        User user = new User();
        user.setPassword("123");
        when(userRepository.findByUserName("marko")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123", "123")).thenReturn(false);
        service.logIn("marko", "123");
    }

    @Test(expected = UnprocessableEntityException.class)
    public void registerInvalidUserException() {
        service.register(null, null);
    }

    @Test(expected = InvalidDataException.class)
    public void registerUserWithNoUsernameAndPasswordException() {
        service.register(new User(), null);
    }

    @Test(expected = InvalidDataException.class)
    public void registerUserWithNoTypeException() {
        User user = new User();
        user.setUserName("Aca");
        user.setPassword("Aca");
        service.register(new User(), null);
    }

    @Test(expected = EntityAlreadyExsistsException.class)
    public void registerAlreadyExistingUser() {
        User user = new User();
        user.setUserName("Aca");
        user.setPassword("Aca");
        when(userRepository.findByUserName("Aca")).thenReturn(Optional.of(user));
        service.register(user, RoleType.USER);
    }

    @Test(expected = EntityNotFoundException.class)
    public void registerUserWithNoExistingRoleException() {
        User user = new User();
        user.setUserName("Aca");
        user.setPassword("Aca");
        when(userRepository.findByUserName("Aca")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("123");
        service.register(user, RoleType.USER);
    }

    @Test(expected = InvalidDataException.class)
    public void parseNullJwtTokenException() {
        service.refreshTokens(null);
    }

    @Test(expected = AccessDeniedException.class)
    public void parseWrongJwtTokenException() {
        service.refreshTokens("");
    }

}
