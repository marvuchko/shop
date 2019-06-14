package com.marko.shop.data.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.marko.shop.data.base.Base;
import com.marko.shop.data.shop.entity.PurchaseList;

@Entity
@Table(name = "shop_user")
public class User extends Base {

    @Column(length = 120)
    private String firstName;

    @Column(length = 120)
    private String lastName;

    @Column(length = 120)
    private String userName;

    private String password;
    
    @Lob
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    private List<PurchaseList> orderLists;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    public User() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PurchaseList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<PurchaseList> orderLists) {
        this.orderLists = orderLists;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
