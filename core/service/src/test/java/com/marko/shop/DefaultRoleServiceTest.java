package com.marko.shop;

import com.marko.shop.data.user.entity.Role;
import com.marko.shop.data.user.repository.RoleRepository;
import com.marko.shop.service.exception.EntityAlreadyExsistsException;
import com.marko.shop.service.exception.UnprocessableEntityException;
import com.marko.shop.service.user.impl.DefaultRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private DefaultRoleService service;

    @Test
    public void findAllWithInvalidParamsReturnsEmptyPage() {
        Page<Role> page = service.findAll(-2, -2);
        assertEquals("Page has elements!", 0, page.getTotalElements());
    }

    @Test(expected = UnprocessableEntityException.class)
    public void createNullRoleException() {
        service.create(null);
    }

    @Test(expected = EntityAlreadyExsistsException.class)
    public void createRoleThatExsistsException() {
        Role role = new Role();
        role.setCaption("ROLE");
        when(roleRepository.findByCaption("ROLE")).thenReturn(Optional.of(role));
        service.create(role);
    }

}
