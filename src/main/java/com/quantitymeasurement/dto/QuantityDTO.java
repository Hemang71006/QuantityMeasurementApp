
package com.quantitymeasurement.dto;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Transfers quantity measurement data between application layers.
 */

public class QuantityDTO {

    private double value;
    private String measurementType;
    private String unit;
    private boolean result;
    private boolean error;
    private String message;
    private String operation;

    public QuantityDTO(
            double value,
            String measurementType,
            String unit) {

        this.value = value;
        this.measurementType = measurementType;
        this.unit = unit;
        this.error = false;
    }

    public QuantityDTO(
            double value,
            String measurementType,
            String unit,
            boolean result,
            String operation) {

        this(value, measurementType, unit);
        this.result = result;
        this.operation = operation;
    }

    public QuantityDTO(
            boolean error,
            String message,
            String operation) {

        this.error = error;
        this.message = message;
        this.operation = operation;
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

    public boolean getResult() {
        return result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean hasError() {
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

    @Override
    public String toString() {
        if (error) {
            return "QuantityDTO{"
                    + "operation='" + operation + '\''
                    + ", error=true"
                    + ", message='" + message + '\''
                    + '}';
        }

        return "QuantityDTO{"
                + "value=" + value
                + ", measurementType='" + measurementType + '\''
                + ", unit='" + unit + '\''
                + ", result=" + result
                + ", operation='" + operation + '\''
                + '}';
    }
}
