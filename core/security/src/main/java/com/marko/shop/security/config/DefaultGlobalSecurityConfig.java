package com.marko.shop.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.marko.shop.security.filters.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = {CorsFilter.class})
public class DefaultGlobalSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.h2.console.path}")
	private String h2ConsolePath;
	
	@Value("${app.swagger-ui.path}")
	private String swaggerUiPath;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(h2ConsolePath + "/*", swaggerUiPath);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable()
			.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.httpBasic().disable();
	}

}
