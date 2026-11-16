package com.quantitymeasurement.model;

import java.io.Serializable;
import java.util.UUID;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Serializable entity used to persist quantity measurement operations and results.
 */

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final long timestamp;

    private final double firstValue;
    private final String firstMeasurementType;
    private final String firstUnit;

    private final Double secondValue;
    private final String secondMeasurementType;
    private final String secondUnit;

    private final String operation;

    private final double resultValue;
    private final String resultMeasurementType;
    private final String resultUnit;

    private final boolean booleanResult;
    private final boolean error;
    private final String message;

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