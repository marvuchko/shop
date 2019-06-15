package com.marko.shop.infrastructure.security;

public class JwtToken {

    private String accessToken;
    
    private String refreshToken;

    public JwtToken() {
	super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
}
