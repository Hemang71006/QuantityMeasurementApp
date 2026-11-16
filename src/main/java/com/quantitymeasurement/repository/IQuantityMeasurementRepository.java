
package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;
import java.util.List;

/*
 * Defines persistence operations for quantity measurement history.
 * Author: Hemang
 * Date: 15-11-2026
 */
public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();

    default List<QuantityMeasurementEntity> getByOperationType(String operation) {
        throw new UnsupportedOperationException("Operation filtering not supported");
    }

    default List<QuantityMeasurementEntity> getByMeasurementType(String measurementType) {
        throw new UnsupportedOperationException("Measurement filtering not supported");
    }

    default long count() {
        return getAllMeasurements().size();
    }

    default void deleteAll() {
        throw new UnsupportedOperationException("Delete operation not supported");
    }

    default String getPoolStatistics() {
        return "Connection pool statistics unavailable";
    }

    default void releaseResources() {
    }
}
