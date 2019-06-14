package com.marko.shop.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {
		DefaultDataLayerConfig.class
})
@EnableJpaRepositories(basePackageClasses = {
		DefaultDataLayerConfig.class
})
public class DefaultDataLayerConfig {}
