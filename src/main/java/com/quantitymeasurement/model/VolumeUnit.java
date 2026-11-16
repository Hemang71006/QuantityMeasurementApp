package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC11 - Volume Measurement
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines volume units and their conversions using litre as the base unit.
 */

import com.quantitymeasurement.interfaces.IMeasurable;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        validateFinite(value);

        double baseValue = value * conversionFactor;

        if (!Double.isFinite(baseValue)) {
            throw new ArithmeticException("Volume conversion overflow");
        }

        return baseValue;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        validateFinite(baseValue);

        double convertedValue = baseValue / conversionFactor;

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException("Volume conversion overflow");
        }

        return convertedValue;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Volume value must be finite");
        }
    }
}