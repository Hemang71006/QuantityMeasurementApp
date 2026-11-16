
package com.quantitymeasurement.dto;

/*
 * Represents the API response for a quantity measurement operation.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class QuantityOperationResponseDTO {

    private double value;
    private String measurementType;
    private String unit;
    private boolean result;
    private boolean error;
    private String message;
    private String operation;

    public QuantityOperationResponseDTO() {
    }

    public QuantityOperationResponseDTO(
            double value,
            String measurementType,
            String unit,
            boolean result,
            boolean error,
            String message,
            String operation) {

        this.value = value;
        this.measurementType = measurementType;
        this.unit = unit;
        this.result = result;
        this.error = error;
        this.message = message;
        this.operation = operation;
    }

    public static QuantityOperationResponseDTO fromQuantityDTO(
            QuantityDTO dto) {

        return new QuantityOperationResponseDTO(
                dto.getValue(),
                dto.getMeasurementType(),
                dto.getUnit(),
                dto.isResult(),
                dto.hasError(),
                dto.getMessage(),
                dto.getOperation()
        );
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
