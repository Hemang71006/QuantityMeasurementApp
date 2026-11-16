
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Measurable Length Units
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines length units and implements the common measurable interface.
 */

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Returns the conversion factor relative to feet.
    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Converts a value in this unit into feet.
    @Override
    public double convertToBaseUnit(double value) {

        validateFinite(value);

        double baseValue = value * conversionFactor;

        validateFiniteResult(baseValue);

        return baseValue;
    }

    // Converts a value from feet into this unit.
    @Override
    public double convertFromBaseUnit(double baseValue) {

        validateFinite(baseValue);

        double convertedValue = baseValue / conversionFactor;

        validateFiniteResult(convertedValue);

        return convertedValue;
    }

    // Returns the name of this length unit.
    @Override
    public String getUnitName() {
        return name();
    }

    // Validates that the supplied measurement is finite.
    private static void validateFinite(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Measurement value must be finite"
            );
        }
    }

    // Validates that the conversion result is finite.
    private static void validateFiniteResult(double value) {

        if (!Double.isFinite(value)) {
            throw new ArithmeticException(
                    "Unit conversion exceeds the finite double range"
            );
        }
    }
}
