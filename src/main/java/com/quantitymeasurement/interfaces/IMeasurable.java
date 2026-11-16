
package com.quantitymeasurement.interfaces;

/*
 * Project Name : Quantity Measurement App
 * Module : UC14 - Temperature Measurement
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines common measurement conversion and optional arithmetic capabilities.
 */

public interface IMeasurable {

    SupportsArithmetic DEFAULT_ARITHMETIC_SUPPORT = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

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
