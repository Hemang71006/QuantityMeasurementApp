
package com.quantitymeasurement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines API documentation metadata for Swagger and OpenAPI.
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Quantity Measurement API",
                version = "1.0",
                description = "REST API for quantity comparison, conversion, arithmetic and history"
        )
)
public class OpenApiConfig {
}
