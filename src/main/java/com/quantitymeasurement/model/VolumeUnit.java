
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Volume Unit Extension
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Adds volume units using the common measurable interface.
 */

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001);

    private final double conversionFactor;

    // Initializes the conversion factor relative to litres.
    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Returns the conversion factor relative to the base unit.
    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Converts a volume value into litres.
    @Override
    public double convertToBaseUnit(double value) {

        validateValue(value);

        double result = value * conversionFactor;

        if (!Double.isFinite(result)) {
            throw new ArithmeticException(
                    "Volume conversion result is out of range"
            );
        }

        return result;
    }

    // Converts a value in litres into this unit.
    @Override
    public double convertFromBaseUnit(double baseValue) {

        validateValue(baseValue);

        double result = baseValue / conversionFactor;

        if (!Double.isFinite(result)) {
            throw new ArithmeticException(
                    "Volume conversion result is out of range"
            );
        }

        return result;
    }

    // Returns the unit name.
    @Override
    public String getUnitName() {
        return name();
    }

    // Validates volume values.
    private void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Volume value must be finite"
            );
        }
    }
}
