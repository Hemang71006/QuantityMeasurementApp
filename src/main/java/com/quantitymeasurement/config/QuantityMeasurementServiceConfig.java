
package com.quantitymeasurement.config;

import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.service.IQuantityMeasurementService;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Registers the existing measurement service with Spring dependency injection.
 */

@Configuration
public class QuantityMeasurementServiceConfig {

    @Bean
    public IQuantityMeasurementService quantityMeasurementService(
            IQuantityMeasurementRepository repository) {
        return new QuantityMeasurementServiceImpl(repository);
    }
}
