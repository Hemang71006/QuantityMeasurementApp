package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC3 - Generic Quantity Length
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents generic length measurements and compares them using a common base unit.
 */

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    // Validates and initializes a measurement.
    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Measurement must be a finite number"
            );
        }

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Length unit cannot be null"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    // Returns the measurement value.
    public double getValue() {
        return value;
    }

    // Returns the measurement unit.
    public LengthUnit getUnit() {
        return unit;
    }

    // Converts the measurement into the base unit feet.
    public double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Compares measurements after converting both to feet.
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(
                this.convertToBaseUnit(),
                other.convertToBaseUnit()
        ) == 0;
    }

    // Generates a hash code using the converted base-unit value.
    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }
}