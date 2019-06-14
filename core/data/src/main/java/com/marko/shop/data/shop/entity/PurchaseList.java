package com.marko.shop.data.shop.entity;

import com.marko.shop.data.base.Base;
import com.marko.shop.data.user.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_list")
public class PurchaseList extends Base {

    @ManyToOne
    @JoinColumn(name = "id_shop_user")
    private User user;

    @OneToMany(mappedBy = "purchaseList")
    private List<Purchase> orders;

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

    public List<Purchase> getOrders() {
        return orders;
    }

    public void setOrders(List<Purchase> orders) {
        this.orders = orders;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
