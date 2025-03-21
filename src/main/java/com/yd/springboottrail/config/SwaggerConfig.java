package com.yd.springboottrail.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    Docket config() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .produces(Stream.of("application/xml", "application/json").collect(Collectors.toSet())).select()
                .apis(RequestHandlerSelectors.basePackage("com.yd.springboottrail.controller"))
                .paths(PathSelectors.any()).build()
                .protocols(Stream.of("http", "https").collect(Collectors.toSet()));
    }
}
