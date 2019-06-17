package com.marko.shop.data.shop.entity;

public enum ShopItemType {

    GROCERIES("GROCERIES"),
    OTHER("OTHER");

    private String type;

    ShopItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
