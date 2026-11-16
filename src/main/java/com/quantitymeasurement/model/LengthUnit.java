
package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC8 - Standalone LengthUnit Enum
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines length units and handles conversions to and from feet.
 */

public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Converts a value in this unit into feet.
    public double convertToBaseUnit(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Measurement value must be finite");
        }

        double baseValue = value * conversionFactor;

        if (!Double.isFinite(baseValue)) {
            throw new ArithmeticException("Base-unit conversion exceeds the finite double range");
        }

        return baseValue;
    }

    // Converts a value from feet into this unit.
    public double convertFromBaseUnit(double baseValue) {
        if (!Double.isFinite(baseValue)) {
            throw new IllegalArgumentException("Base-unit value must be finite");
        }

        double convertedValue = baseValue / conversionFactor;

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException("Unit conversion exceeds the finite double range");
        }

        return convertedValue;
    }
}