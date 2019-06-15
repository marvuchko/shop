package com.marko.shop.data.user.entity;

public enum RoleType {

    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");
    
    private final String type;

    private RoleType(String type) {
	this.type = type;
    }

    public String getType() {
        return type;
    }

}
