
package com.quantitymeasurement.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for quantity measurement repository factory.
 * Author: Hemang
 * Date: 15-11-2026
 */
class QuantityMeasurementRepositoryFactoryTest {

    @Test
    void givenDatabaseType_whenCreated_shouldReturnDatabaseRepository() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementRepositoryFactory.createRepository(
                        "database"
                );

        assertInstanceOf(
                QuantityMeasurementDatabaseRepository.class,
                repository
        );
    }

    @Test
    void givenJdbcType_whenCreated_shouldReturnDatabaseRepository() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementRepositoryFactory.createRepository(
                        "jdbc"
                );

        assertInstanceOf(
                QuantityMeasurementDatabaseRepository.class,
                repository
        );
    }

    @Test
    void givenH2Type_whenCreated_shouldReturnDatabaseRepository() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementRepositoryFactory.createRepository(
                        "h2"
                );

        assertInstanceOf(
                QuantityMeasurementDatabaseRepository.class,
                repository
        );
    }

    @Test
    void givenCacheType_whenCreated_shouldReturnCacheRepository() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementRepositoryFactory.createRepository(
                        "cache"
                );

        assertInstanceOf(
                QuantityMeasurementCacheRepository.class,
                repository
        );
    }

    @Test
    void givenUppercaseType_whenCreated_shouldIgnoreCase() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementRepositoryFactory.createRepository(
                        "DATABASE"
                );

        assertInstanceOf(
                QuantityMeasurementDatabaseRepository.class,
                repository
        );
    }

    @Test
    void givenUnsupportedType_whenCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementRepositoryFactory.createRepository(
                        "mongodb"
                )
        );
    }

    @Test
    void givenNullType_whenCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementRepositoryFactory.createRepository(
                        null
                )
        );
    }

    @Test
    void givenEmptyType_whenCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementRepositoryFactory.createRepository(
                        " "
                )
        );
    }
}
