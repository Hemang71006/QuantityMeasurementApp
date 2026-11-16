
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Measurable Weight Units
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines weight units and implements the common measurable interface.
 */

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Returns the conversion factor relative to kilograms.
    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Converts a value in this unit into kilograms.
    @Override
    public double convertToBaseUnit(double value) {

        validateFinite(value);

        double baseValue = value * conversionFactor;

        validateFiniteResult(baseValue);

        return baseValue;
    }

    // Converts a value from kilograms into this unit.
    @Override
    public double convertFromBaseUnit(double baseValue) {

        validateFinite(baseValue);

        double convertedValue = baseValue / conversionFactor;

        validateFiniteResult(convertedValue);

        return convertedValue;
    }

    // Returns the name of this weight unit.
    @Override
    public String getUnitName() {
        return name();
    }

    // Validates that the supplied measurement is finite.
    private static void validateFinite(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Weight value must be finite"
            );
        }
    }

    // Validates that the conversion result is finite.
    private static void validateFiniteResult(double value) {

        if (!Double.isFinite(value)) {
            throw new ArithmeticException(
                    "Weight conversion exceeds the finite double range"
            );
        }
    }
}
