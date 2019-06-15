package com.marko.shop.infrastructure.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUserPrincipal {
    
    private Long userId;
    
    private String userName;
    
    private String password;
    
    private List<GrantedAuthority> authorities;

    public AuthenticatedUserPrincipal() {
	super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
   
}
