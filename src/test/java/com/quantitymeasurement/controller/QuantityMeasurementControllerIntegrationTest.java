
package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Integration tests for controller, service and JDBC repository layers.
 * Author: Hemang
 * Date: 15-11-2026
 */
class QuantityMeasurementControllerIntegrationTest {

    private QuantityMeasurementDatabaseRepository repository;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    @Test
    void givenValidMeasurement_whenConverted_shouldPersistHistory() {
        QuantityDTO measurement =
                new QuantityDTO(1.0, "LENGTH", "FEET");

        controller.convert(measurement, "INCHES");

        assertEquals(1, repository.count());

        assertEquals(
                "CONVERT",
                repository.getAllMeasurements().get(0).getOperation()
        );
    }

    @Test
    void givenValidMeasurements_whenCompared_shouldPersistHistory() {
        QuantityDTO first =
                new QuantityDTO(1.0, "LENGTH", "FEET");

        QuantityDTO second =
                new QuantityDTO(12.0, "LENGTH", "INCHES");

        controller.compare(first, second);

        assertEquals(1, repository.count());
    }

    @Test
    void givenMultipleOperations_whenExecuted_shouldPersistAllHistory() {
        QuantityDTO first =
                new QuantityDTO(5.0, "LENGTH", "FEET");

        QuantityDTO second =
                new QuantityDTO(12.0, "LENGTH", "INCHES");

        controller.compare(first, second);
        controller.convert(first, "INCHES");
        controller.add(first, second);

        assertEquals(3, repository.count());
    }
}
