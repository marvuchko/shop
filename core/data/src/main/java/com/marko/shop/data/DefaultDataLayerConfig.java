package com.marko.shop.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {
		DataPackage.class
})
@EnableJpaRepositories(basePackageClasses = {
		DataPackage.class
})
public class DefaultDataLayerConfig {}
