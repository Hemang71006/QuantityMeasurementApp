
package com.quantitymeasurement.repository;

import com.quantitymeasurement.exception.DatabaseException;
import com.quantitymeasurement.model.QuantityMeasurementEntity;
import com.quantitymeasurement.util.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Integration tests for JDBC persistence, database schema and connection pooling.
 * Author: Hemang
 * Date: 15-11-2026
 */
class QuantityMeasurementDatabaseIntegrationTest {

    private final QuantityMeasurementDatabaseRepository repository =
            new QuantityMeasurementDatabaseRepository();

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void givenSqlInjectionInput_whenFiltering_shouldReturnNoRecords() {
        repository.save(createEntity("ADD", "Normal record"));

        List<QuantityMeasurementEntity> results =
                repository.getByOperationType(
                        "ADD' OR '1'='1"
                );

        assertTrue(results.isEmpty());
    }

    @Test
    void givenInvalidRecord_whenSaved_shouldRollbackTransaction() {
        String oversizedMessage = "A".repeat(1001);

        QuantityMeasurementEntity entity =
                createEntity("ADD", oversizedMessage);

        assertThrows(
                DatabaseException.class,
                () -> repository.save(entity)
        );

        assertEquals(0, repository.count());
    }

    @Test
    void givenDatabase_whenInitialized_shouldContainRequiredTableAndIndexes()
            throws Exception {

        try (Connection connection = ConnectionPool.getConnection()) {

            DatabaseMetaData metadata = connection.getMetaData();

            try (ResultSet tables = metadata.getTables(
                    null,
                    null,
                    "QUANTITY_MEASUREMENTS",
                    new String[]{"TABLE"}
            )) {
                assertTrue(tables.next());
            }

            List<String> indexNames = new ArrayList<>();

            try (ResultSet indexes = metadata.getIndexInfo(
                    null,
                    null,
                    "QUANTITY_MEASUREMENTS",
                    false,
                    false
            )) {
                while (indexes.next()) {
                    String indexName = indexes.getString("INDEX_NAME");

                    if (indexName != null) {
                        indexNames.add(indexName.toUpperCase());
                    }
                }
            }

            assertTrue(
                    indexNames.contains("IDX_MEASUREMENT_OPERATION")
            );

            assertTrue(
                    indexNames.contains("IDX_MEASUREMENT_TYPE")
            );
        }
    }

    @Test
    void givenDatabase_whenRequested_shouldReturnConnectionPoolStatistics() {
        String statistics = repository.getPoolStatistics();

        assertNotNull(statistics);
        assertTrue(statistics.contains("Active Connections"));
        assertTrue(statistics.contains("Idle Connections"));
        assertTrue(statistics.contains("Total Connections"));
    }

    @Test
    void givenMultipleThreads_whenSaving_shouldPersistAllRecords() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(30),
                () -> {
                    int numberOfRecords = 25;

                    ExecutorService executor =
                            Executors.newFixedThreadPool(5);

                    try {
                        List<Future<?>> tasks = new ArrayList<>();

                        for (int i = 0; i < numberOfRecords; i++) {
                            final int recordNumber = i;

                            tasks.add(executor.submit(
                                    () -> repository.save(
                                            createEntity(
                                                    "ADD",
                                                    "Concurrent record "
                                                            + recordNumber
                                            )
                                    )
                            ));
                        }

                        for (Future<?> task : tasks) {
                            task.get();
                        }

                    } finally {
                        executor.shutdownNow();
                    }

                    assertEquals(numberOfRecords, repository.count());
                }
        );
    }

    private QuantityMeasurementEntity createEntity(
            String operation,
            String message) {

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
                message
        );
    }
}
