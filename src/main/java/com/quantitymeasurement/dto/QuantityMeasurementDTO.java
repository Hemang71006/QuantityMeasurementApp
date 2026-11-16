
package com.quantitymeasurement.dto;

import com.quantitymeasurement.model.QuantityMeasurementEntity;

/*
 * Transfers quantity measurement history between entity and API layers.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class QuantityMeasurementDTO {

    private String id;
    private long timestamp;

    private double firstValue;
    private String firstMeasurementType;
    private String firstUnit;

    private Double secondValue;
    private String secondMeasurementType;
    private String secondUnit;

    private String operation;

    private double resultValue;
    private String resultMeasurementType;
    private String resultUnit;

    private boolean booleanResult;
    private boolean error;
    private String message;

    public QuantityMeasurementDTO() {
    }

    public static QuantityMeasurementDTO fromEntity(
            QuantityMeasurementEntity entity) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.id = entity.getId();
        dto.timestamp = entity.getTimestamp();

        dto.firstValue = entity.getFirstValue();
        dto.firstMeasurementType = entity.getFirstMeasurementType();
        dto.firstUnit = entity.getFirstUnit();

        dto.secondValue = entity.getSecondValue();
        dto.secondMeasurementType = entity.getSecondMeasurementType();
        dto.secondUnit = entity.getSecondUnit();

        dto.operation = entity.getOperation();

        dto.resultValue = entity.getResultValue();
        dto.resultMeasurementType = entity.getResultMeasurementType();
        dto.resultUnit = entity.getResultUnit();

        dto.booleanResult = entity.isBooleanResult();
        dto.error = entity.isError();
        dto.message = entity.getMessage();

        return dto;
    }

    public QuantityMeasurementEntity toEntity() {

        return new QuantityMeasurementEntity(
                id,
                timestamp,
                firstValue,
                firstMeasurementType,
                firstUnit,
                secondValue,
                secondMeasurementType,
                secondUnit,
                operation,
                resultValue,
                resultMeasurementType,
                resultUnit,
                booleanResult,
                error,
                message
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public String getFirstMeasurementType() {
        return firstMeasurementType;
    }

    public void setFirstMeasurementType(String firstMeasurementType) {
        this.firstMeasurementType = firstMeasurementType;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }

    public String getSecondMeasurementType() {
        return secondMeasurementType;
    }

    public void setSecondMeasurementType(String secondMeasurementType) {
        this.secondMeasurementType = secondMeasurementType;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultMeasurementType() {
        return resultMeasurementType;
    }

    public void setResultMeasurementType(String resultMeasurementType) {
        this.resultMeasurementType = resultMeasurementType;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public boolean isBooleanResult() {
        return booleanResult;
    }

    public void setBooleanResult(boolean booleanResult) {
        this.booleanResult = booleanResult;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
