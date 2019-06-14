package com.marko.shop.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
		DefaultServiceConfig.class
})
public class DefaultServiceConfig {}
