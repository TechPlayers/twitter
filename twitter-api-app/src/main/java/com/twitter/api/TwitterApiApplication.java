package com.twitter.api;

import com.twitter.api.config.BaseConfig;
import com.twitter.api.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({BaseConfig.class, SwaggerConfig.class})
public class TwitterApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitterApiApplication.class, args);
    }
}
