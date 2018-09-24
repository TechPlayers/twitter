package com.twitter.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Base configuration for twitter API, handles configuration of SpringBoot  .
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.twitter.*"})
@EntityScan(basePackages = {"com.twitter.model.dao"})
@EnableJpaRepositories(basePackages = {"com.twitter.repository"})
public class BaseConfig {
}
