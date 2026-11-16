
package com.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/*
 * Represents requests containing two quantities for an operation.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class QuantityOperationRequestDTO {

    @NotNull(message = "First quantity is required")
    @Valid
    private QuantityInputDTO first;

    @NotNull(message = "Second quantity is required")
    @Valid
    private QuantityInputDTO second;

    private String targetUnit;

    public QuantityOperationRequestDTO() {
    }

    public QuantityOperationRequestDTO(
            QuantityInputDTO first,
            QuantityInputDTO second,
            String targetUnit) {
        this.first = first;
        this.second = second;
        this.targetUnit = targetUnit;
    }

    public QuantityInputDTO getFirst() {
        return first;
    }

    public void setFirst(QuantityInputDTO first) {
        this.first = first;
    }

    public QuantityInputDTO getSecond() {
        return second;
    }

    public void setSecond(QuantityInputDTO second) {
        this.second = second;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }
}
