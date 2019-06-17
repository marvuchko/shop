package com.marko.shop;

import com.marko.shop.data.shop.repository.PurchaseListRepository;
import com.marko.shop.data.shop.repository.ShopItemRepository;
import com.marko.shop.data.user.entity.User;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.exception.EntityNotFoundException;
import com.marko.shop.service.exception.InvalidDataException;
import com.marko.shop.service.shop.impl.DefaultPurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPurchaseServiceTest {

    @Mock
    private PurchaseListRepository purchaseListRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShopItemRepository shopItemRepository;

    @InjectMocks
    private DefaultPurchaseService service;

    @Test
    public void findAllPurchaseListsForUserShouldReturnEpmtyPageIfArgsAreNotValid() {
        service.findAllPurchaseListsForUser(-2, -2, 1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findAllPurchaseListsForUserShouldThrowExceptionIfInvalidUserId() {
        when(userRepository.findById(-1L)).thenReturn(Optional.empty());
        service.findAllPurchaseListsForUser(0, 20, -1L);
    }

    @Test(expected = InvalidDataException.class)
    public void purchaseShouldThrowExceptionIfItemsAreNull() {
        service.purchaseItems(null, -1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void purchaseShouldThrowExceptionIfUserNotFound() {
        when(userRepository.findById(-1L)).thenReturn(Optional.empty());
        service.purchaseItems(Arrays.asList(1L, 2L), -1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void purchaseShouldThrowExceptionIfNoItemsFoundForGivenIds() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(shopItemRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(new ArrayList<>());
        service.purchaseItems(Arrays.asList(1L, 2L), 1L);
    }

}
