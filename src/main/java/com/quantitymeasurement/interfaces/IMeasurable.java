
package com.quantitymeasurement.interfaces;

import java.util.Locale;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines common measurement conversion, arithmetic capabilities and unit lookup.
 */

public interface IMeasurable {

    SupportsArithmetic DEFAULT_ARITHMETIC_SUPPORT = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // Returns the measurement category of this unit.
    default String getMeasurementType() {
        return getClass().getSimpleName()
                .replace("Unit", "")
                .toUpperCase(Locale.ROOT);
    }

    // Resolves a unit name within the current measurement category.
    @SuppressWarnings({"unchecked", "rawtypes"})
    default IMeasurable getUnit(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            throw new IllegalArgumentException(
                    "Unit name cannot be null or blank"
            );
        }

        try {
            Class<? extends Enum> enumClass =
                    (Class<? extends Enum>) getClass();

            return (IMeasurable) Enum.valueOf(
                    enumClass,
                    unitName.trim().toUpperCase(Locale.ROOT)
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid unit '" + unitName
                            + "' for measurement type "
                            + getMeasurementType(),
                    exception
            );
        }
    }

    // Returns whether this measurement category supports arithmetic.
    default boolean supportsArithmetic() {
        return DEFAULT_ARITHMETIC_SUPPORT.isSupported();
    }

    // Returns whether addition is supported.
    default boolean supportsAddition() {
        return supportsArithmetic();
    }

    // Returns whether subtraction is supported.
    default boolean supportsSubtraction() {
        return supportsArithmetic();
    }

    // Returns whether division is supported.
    default boolean supportsDivision() {
        return supportsArithmetic();
    }

    // Allows individual measurement categories to validate operations.
    default void validateOperationSupport(String operation) {
        // Existing measurement categories support arithmetic by default.
    }
}
