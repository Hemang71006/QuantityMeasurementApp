
package com.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Carries a quantity and its requested target unit for conversion.
 */

public class QuantityConversionRequestDTO {

    @NotNull
    @Valid
    private QuantityInputDTO quantity;

    @NotBlank
    private String targetUnit;

    public QuantityConversionRequestDTO() {
    }

    public QuantityConversionRequestDTO(
            QuantityInputDTO quantity,
            String targetUnit) {
        this.quantity = quantity;
        this.targetUnit = targetUnit;
    }

    public QuantityInputDTO getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityInputDTO quantity) {
        this.quantity = quantity;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }
}
