
package com.quantitymeasurement.model;

/*
 * Project: Quantity Measurement Application
 * Description: Defines weight units and their conversion responsibilities.
 * Author: Hemang
 * Date: 15-11-2026
 */

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        validateFinite(value);
        double result = value * conversionFactor;
        validateFinite(result);
        return result;
    }

    public double convertFromBaseUnit(double baseValue) {
        validateFinite(baseValue);
        double result = baseValue / conversionFactor;
        validateFinite(result);
        return result;
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be finite");
        }
    }
}
