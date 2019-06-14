package com.marko.shop.data;

import com.marko.shop.data.shop.ShopPackage;
import com.marko.shop.data.user.UserPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EntityScan(basePackageClasses = {
        UserPackage.class,
        ShopPackage.class
})
@EnableJpaRepositories(basePackageClasses = {
        UserPackage.class,
        ShopPackage.class
})
public class DataLayerConfig {
}
