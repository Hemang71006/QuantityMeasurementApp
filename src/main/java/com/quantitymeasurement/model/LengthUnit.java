package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC4 - Extended Unit Support
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines supported length units and their conversion factors to feet.
 */

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.393701 / 12.0);

    // Conversion factor relative to the base unit feet.
    private final double conversionFactor;

    // Initializes the conversion factor for each unit.
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Returns the conversion factor.
    public double getConversionFactor() {
        return conversionFactor;
    }
}