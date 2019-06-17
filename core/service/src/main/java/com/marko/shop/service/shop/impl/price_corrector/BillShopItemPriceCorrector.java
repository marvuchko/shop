package com.marko.shop.service.shop.impl.price_corrector;

import com.marko.shop.data.shop.entity.ShopItem;

public class BillShopItemPriceCorrector extends  ShopItemPriceCorrector {

    private final Float billDiscount;

    private final Float perMoneyAmmount;

    public BillShopItemPriceCorrector(Float billDiscount, Float perMoneyAmmount) {
        this.billDiscount = billDiscount;
        this.perMoneyAmmount = perMoneyAmmount;
    }

    @Override
    protected void performCalculation(ItemWithUserAdapter itemWithUserAdapter) {
        if (itemWithUserAdapter == null) return;
        ShopItem shopItem = itemWithUserAdapter.getShopItem();
        Float newPrice = shopItem.getPrice() - shopItem.getPrice() / perMoneyAmmount * billDiscount;
        shopItem.setPrice(newPrice);
    }
}
