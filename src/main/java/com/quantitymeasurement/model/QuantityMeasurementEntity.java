
package com.quantitymeasurement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;

import java.io.Serializable;
import java.util.UUID;

/*
 * JPA entity for storing quantity measurement operations and results.
 * Author: Hemang
 * Date: 15-11-2026
 */
@Entity
@Table(
        name = "quantity_measurements",
        indexes = {
                @Index(name = "idx_measurement_operation", columnList = "operation"),
                @Index(name = "idx_measurement_type", columnList = "first_measurement_type"),
                @Index(name = "idx_measurement_timestamp", columnList = "timestamp")
        }
)
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private long timestamp;

    @Column(name = "first_value", nullable = false)
    private double firstValue;

    @Column(name = "first_measurement_type")
    private String firstMeasurementType;

    @Column(name = "first_unit")
    private String firstUnit;

    @Column(name = "second_value")
    private Double secondValue;

    @Column(name = "second_measurement_type")
    private String secondMeasurementType;

    @Column(name = "second_unit")
    private String secondUnit;

    @Column(nullable = false)
    private String operation;

    @Column(name = "result_value")
    private double resultValue;

    @Column(name = "result_measurement_type")
    private String resultMeasurementType;

    @Column(name = "result_unit")
    private String resultUnit;

    @Column(name = "boolean_result")
    private boolean booleanResult;

    @Column(name = "is_error")
    private boolean error;

    @Column(length = 2000)
    private String message;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            double firstValue,
            String firstMeasurementType,
            String firstUnit,
            Double secondValue,
            String secondMeasurementType,
            String secondUnit,
            String operation,
            double resultValue,
            String resultMeasurementType,
            String resultUnit,
            boolean booleanResult,
            boolean error,
            String message) {

        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.firstValue = firstValue;
        this.firstMeasurementType = firstMeasurementType;
        this.firstUnit = firstUnit;
        this.secondValue = secondValue;
        this.secondMeasurementType = secondMeasurementType;
        this.secondUnit = secondUnit;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultMeasurementType = resultMeasurementType;
        this.resultUnit = resultUnit;
        this.booleanResult = booleanResult;
        this.error = error;
        this.message = message;
    }

    public QuantityMeasurementEntity(
            String id,
            long timestamp,
            double firstValue,
            String firstMeasurementType,
            String firstUnit,
            Double secondValue,
            String secondMeasurementType,
            String secondUnit,
            String operation,
            double resultValue,
            String resultMeasurementType,
            String resultUnit,
            boolean booleanResult,
            boolean error,
            String message) {

        this.id = id;
        this.timestamp = timestamp;
        this.firstValue = firstValue;
        this.firstMeasurementType = firstMeasurementType;
        this.firstUnit = firstUnit;
        this.secondValue = secondValue;
        this.secondMeasurementType = secondMeasurementType;
        this.secondUnit = secondUnit;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultMeasurementType = resultMeasurementType;
        this.resultUnit = resultUnit;
        this.booleanResult = booleanResult;
        this.error = error;
        this.message = message;
    }

    @PrePersist
    public void initializeDefaults() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        if (timestamp == 0) {
            timestamp = System.currentTimeMillis();
        }
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public String getFirstMeasurementType() {
        return firstMeasurementType;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public String getSecondMeasurementType() {
        return secondMeasurementType;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public String getOperation() {
        return operation;
    }

    public double getResultValue() {
        return resultValue;
    }

    public String getResultMeasurementType() {
        return resultMeasurementType;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public boolean isBooleanResult() {
        return booleanResult;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id='" + id + '\'' +
                ", operation='" + operation + '\'' +
                ", firstValue=" + firstValue +
                ", firstUnit='" + firstUnit + '\'' +
                ", secondValue=" + secondValue +
                ", secondUnit='" + secondUnit + '\'' +
                ", resultValue=" + resultValue +
                ", resultUnit='" + resultUnit + '\'' +
                ", booleanResult=" + booleanResult +
                ", error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
