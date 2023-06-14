package com.service.project_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*/**")
                .allowedOrigins("https://dxmsjilz0lci1.cloudfront.net/","http://99.80.152.125/","https://99.80.152.125/","http://ec2-99-80-152-125.eu-west-1.compute.amazonaws.com/","http://ec2-99-80-152-125.eu-west-1.compute.amazonaws.com","http://ec2-99-80-152-125.eu-west-1.compute.amazonaws.com/myapi/","https://ec2-99-80-152-125.eu-west-1.compute.amazonaws.com/","https://d1sxv3odgzkhr7.cloudfront.net/","http://localhost:80/","https://localhost:80/","http://localhost:4200/","https://localhost:4200/")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}