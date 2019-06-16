package com.marko.shop.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.marko.shop.security.filters.CorsFilter;
import com.marko.shop.security.jwt.JwtTokenManager;

@Configuration
@ComponentScan(basePackageClasses = {CorsFilter.class, JwtTokenManager.class})
public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}
	
}
