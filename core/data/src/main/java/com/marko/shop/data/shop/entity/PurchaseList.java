package com.marko.shop.data.shop.entity;

import com.marko.shop.data.base.Base;
import com.marko.shop.data.user.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase_list")
public class PurchaseList extends Base {

    @ManyToOne
    @JoinColumn(name = "id_shop_user")
    private User user;

    @OneToMany(mappedBy = "purchaseList", cascade = {
    		CascadeType.PERSIST,
    		CascadeType.MERGE
    })
    private List<Purchase> purchases;

    private Float totalPrice;

    public PurchaseList() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
