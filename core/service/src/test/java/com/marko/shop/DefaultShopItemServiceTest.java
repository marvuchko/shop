package com.marko.shop;

import com.marko.shop.data.shop.entity.ShopItem;
import com.marko.shop.data.shop.entity.ShopItemType;
import com.marko.shop.data.shop.repository.ShopItemRepository;
import com.marko.shop.data.user.repository.UserRepository;
import com.marko.shop.service.exception.EntityAlreadyExsistsException;
import com.marko.shop.service.exception.EntityNotFoundException;
import com.marko.shop.service.exception.UnprocessableEntityException;
import com.marko.shop.service.shop.PriceManager;
import com.marko.shop.service.shop.impl.DefaultShopItemService;
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
public class DefaultShopItemServiceTest {

    @Mock
    private ShopItemRepository shopItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultShopItemService service;

    @Test
    public void invalidArgumentsShouldReturnBlankPage() {
        Page<ShopItem> shopItemPage = service.findAll(-2, -32, null);
        assertEquals("Page has elements", 0, shopItemPage.getTotalElements());
    }

    @Test(expected = EntityNotFoundException.class)
    public void invalidUserArgumenShoudThrowException() {
        when(userRepository.findById(-1L)).thenReturn(Optional.empty());
        service.findAll(0, 20, -1L);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void createNullShopItemShouldThrowException() {
        service.create(null, null);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void nullUsernameShouldThrowException() {
        ShopItem shopItem = new ShopItem();
        service.create(shopItem, ShopItemType.OTHER);
    }

    @Test(expected = EntityAlreadyExsistsException.class)
    public void createShopitemWithTakenNameShouldThrowException() {
        ShopItem shopItem = new ShopItem();
        shopItem.setName("Pepsi");
        when(shopItemRepository.findByName("Pepsi")).thenReturn(Optional.of(shopItem));
        service.create(shopItem, ShopItemType.GROCERIES);
    }

}
