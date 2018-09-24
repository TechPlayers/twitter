package com.twitter.api.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger/Springfox.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return (new Docket(DocumentationType.SWAGGER_2)).select().
                apis(this.apis()).paths(this.paths()).build().apiInfo(this.apiInfo());
    }

    protected ApiInfo apiInfo() {
        return new ApiInfo(this.title(), this.description(), "",
                "", new Contact("", "", ""), "", "", Lists.<VendorExtension>newArrayList());
    }

    public Predicate<String> paths() {
        return PathSelectors.ant("/api/v1/**");
    }

    public Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("com.twitter");
    }

    public String description() {
        return "Provides a set of endpoints for twitter";
    }

    public String title() {
        return "Twitter API";
    }
}