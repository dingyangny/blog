package com.yd.springboottrail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class SpringbootTrailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTrailApplication.class, args);
    }

}
