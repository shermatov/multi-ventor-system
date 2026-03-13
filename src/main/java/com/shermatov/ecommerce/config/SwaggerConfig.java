package com.shermatov.ecommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecommerseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Multi-Vendor Platform API")
                        .description("API documentation for Ecommerce")
                        .version("v1.0")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub repository")
                        .url("https://github.com/shermatov/multi-ventor-system")
                );
    }
}