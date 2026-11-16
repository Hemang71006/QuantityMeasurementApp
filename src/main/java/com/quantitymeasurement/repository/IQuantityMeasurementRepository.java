package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;

import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines the contract for storing and retrieving measurement history.
 */

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();
}