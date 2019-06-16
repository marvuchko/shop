package com.marko.shop.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.marko.shop.security.filters.JwtFilter;
import com.marko.shop.security.jwt.JwtTokenManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.database-console}")
	private String databaseConsole;
	
	@Value("${app.swagger-ui.path}")
	private String swaggerUiPath;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable()
			.csrf().disable()
			.requestMatchers()
				.antMatchers("/auth/employee/register/**")
				.antMatchers("/role/**")
				.antMatchers("/shop/**")
			.and()
			.authorizeRequests()
				.antMatchers("/auth/employee/register/**").authenticated()
				.antMatchers("/role/**").authenticated()
				.antMatchers("/shop/**").authenticated()
			.and()
			.addFilterBefore(new JwtFilter(jwtTokenManager), BasicAuthenticationFilter.class)
			.httpBasic().disable();
	}

}
