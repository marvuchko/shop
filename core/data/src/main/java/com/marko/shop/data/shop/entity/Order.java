package com.marko.shop.data.shop.entity;

import com.marko.shop.data.base.Base;
import com.marko.shop.data.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_shop_item")
    private ShopItem shopItem;

    @ManyToOne
    @JoinColumn(name = "id_order_list")
    private OrderList orderList;

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }
}
