package com.supplyflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI supplyFlowOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title(
                                        "SupplyFlow API"
                                )
                                .description(
                                        """
                                        Intelligent Supply and Inventory
                                        Management Platform API.

                                        SupplyFlow helps manage products,
                                        suppliers, stock movements and
                                        purchase orders.

                                        The API also provides critical stock
                                        detection and automatic order
                                        suggestions.
                                        """
                                )
                                .version("1.0.0")
                                .license(
                                        new License()
                                                .name("MIT License")
                                )
                );
    }
}