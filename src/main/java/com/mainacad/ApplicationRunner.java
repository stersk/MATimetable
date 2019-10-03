package com.mainacad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApplicationRunner
{
    public static void main( String[] args ) {
        SpringApplication context = new SpringApplication(ApplicationRunner.class);
        context.run(args);
    }
}
