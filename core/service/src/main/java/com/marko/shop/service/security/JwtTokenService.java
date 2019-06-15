package com.marko.shop.service.security;

import com.marko.shop.infrastructure.security.JwtToken;

public interface JwtTokenService {

    JwtToken generateToken();
    
}
