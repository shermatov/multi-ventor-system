package com.shermatov.ecommerce.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.frontend")
public record FrontendProperties(
        String baseUrl,
        String resetPasswordPath
) {
}