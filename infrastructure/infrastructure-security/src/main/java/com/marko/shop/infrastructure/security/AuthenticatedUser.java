package com.marko.shop.infrastructure.security;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUser implements Principal {
    
    private Long userId;
    
    private String userName;
    
    private List<GrantedAuthority> authorities;

    public AuthenticatedUser() {
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

	public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

	@Override
	public String getName() {
		return getUserName();
	}
   
}
