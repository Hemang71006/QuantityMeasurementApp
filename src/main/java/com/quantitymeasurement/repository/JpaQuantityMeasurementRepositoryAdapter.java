
package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Adapts the Spring Data JPA repository to the existing repository interface.
 */

@Repository
public class JpaQuantityMeasurementRepositoryAdapter
        implements IQuantityMeasurementRepository {

    private final QuantityMeasurementJpaRepository jpaRepository;

    public JpaQuantityMeasurementRepositoryAdapter(
            QuantityMeasurementJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        jpaRepository.save(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return jpaRepository.findAll(
                Sort.by(Sort.Direction.DESC, "timestamp")
        );
    }

    @Override
    public List<QuantityMeasurementEntity> getByOperationType(
            String operation) {
        return jpaRepository.findByOperationOrderByTimestampDesc(operation);
    }

    @Override
    public List<QuantityMeasurementEntity> getByMeasurementType(
            String measurementType) {
        return jpaRepository
                .findByFirstMeasurementTypeOrderByTimestampDesc(measurementType);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    // Spring manages database connections, so no manual cleanup is required.
    @Override
    public void releaseResources() {
    }
}
