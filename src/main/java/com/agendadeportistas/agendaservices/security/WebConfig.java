package com.agendadeportistas.agendaservices.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.path}")
    private String frontendPath;

    @PostConstruct
    public void printFrontendPath() {
        System.out.println("Frontend path: " + frontendPath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontendPath)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
