package com.marko.shop.data.shop.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.marko.shop.data.base.Base;

@Entity
@Table(name = "purchase")
public class Purchase extends Base {
	
	@ManyToOne
	@JoinColumn(name = "id_shop_item")
	private ShopItem shopItem;

    @ManyToOne
    @JoinColumn(name = "id_purchase_list")
    private PurchaseList purchaseList;

    public Purchase() {
        super();
    }

	public ShopItem getShopItem() {
		return shopItem;
	}

	public void setShopItem(ShopItem shopItem) {
		this.shopItem = shopItem;
	}

	public PurchaseList getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(PurchaseList purchaseList) {
		this.purchaseList = purchaseList;
	}
}
