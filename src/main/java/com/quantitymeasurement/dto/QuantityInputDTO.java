
package com.quantitymeasurement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Represents incoming quantity measurement data.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class QuantityInputDTO {

    @NotNull(message = "Value is required")
    private Double value;

    @NotBlank(message = "Measurement type is required")
    private String measurementType;

    @NotBlank(message = "Unit is required")
    private String unit;

    public QuantityInputDTO() {
    }

    public QuantityInputDTO(
            Double value,
            String measurementType,
            String unit) {
        this.value = value;
        this.measurementType = measurementType;
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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
}
