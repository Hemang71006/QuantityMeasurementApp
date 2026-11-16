
package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Spring Data JPA repository for quantity measurement history.
 * Author: Hemang
 * Date: 15-11-2026
 */
public interface QuantityMeasurementJpaRepository
        extends JpaRepository<QuantityMeasurementEntity, String> {

    List<QuantityMeasurementEntity> findByOperationOrderByTimestampDesc(
            String operation);

    List<QuantityMeasurementEntity>
    findByFirstMeasurementTypeOrderByTimestampDesc(
            String measurementType);

    List<QuantityMeasurementEntity> findByErrorTrueOrderByTimestampDesc();
}
