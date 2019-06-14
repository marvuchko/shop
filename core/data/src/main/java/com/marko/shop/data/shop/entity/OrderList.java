package com.marko.shop.data.shop.entity;

import com.marko.shop.data.base.Base;
import com.marko.shop.data.user.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_list")
public class OrderList extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_shop_user")
    private User user;

    @OneToMany(mappedBy = "orderList")
    private List<Order> orders;

    private Float totalPrice;

    public OrderList() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
