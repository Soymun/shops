package com.example.shop.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.shop.Entity")
@EnableJpaRepositories(basePackages = {"com.example.shop.Repository"})
@ComponentScan({"com.example.shop.Repository"})
public class SystemTestingJpaConfig {
}
