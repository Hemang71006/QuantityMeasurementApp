
package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for JDBC quantity measurement repository.
 * Author: Hemang
 * Date: 15-11-2026
 */
class QuantityMeasurementDatabaseRepositoryTest {

    private final QuantityMeasurementDatabaseRepository repository =
            new QuantityMeasurementDatabaseRepository();

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void givenMeasurement_whenSaved_shouldRetrieveIt() {
        QuantityMeasurementEntity entity = createEntity("ADD");

        repository.save(entity);

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertEquals(1, measurements.size());
        assertEquals(entity.getId(), measurements.get(0).getId());
        assertEquals(15.0, measurements.get(0).getResultValue());
    }

    @Test
    void givenMultipleOperations_whenFiltered_shouldReturnMatchingRecords() {
        repository.save(createEntity("ADD"));
        repository.save(createEntity("SUBTRACT"));
        repository.save(createEntity("ADD"));

        List<QuantityMeasurementEntity> results =
                repository.getByOperationType("ADD");

        assertEquals(2, results.size());
    }

    @Test
    void givenMeasurements_whenCountRequested_shouldReturnRecordCount() {
        repository.save(createEntity("ADD"));
        repository.save(createEntity("SUBTRACT"));

        assertEquals(2, repository.count());
    }

    @Test
    void givenMeasurements_whenDeleted_shouldRemoveAllRecords() {
        repository.save(createEntity("ADD"));
        repository.save(createEntity("SUBTRACT"));

        repository.deleteAll();

        assertEquals(0, repository.count());
    }

    @Test
    void givenMeasurementType_whenFiltered_shouldReturnMatchingRecords() {
        repository.save(createEntity("ADD"));

        List<QuantityMeasurementEntity> results =
                repository.getByMeasurementType("Length");

        assertEquals(1, results.size());
        assertEquals("Length", results.get(0).getFirstMeasurementType());
    }

    private QuantityMeasurementEntity createEntity(String operation) {
        return new QuantityMeasurementEntity(
                10.0,
                "Length",
                "FEET",
                5.0,
                "Length",
                "FEET",
                operation,
                15.0,
                "Length",
                "FEET",
                false,
                false,
                "Test measurement"
        );
    }
}
