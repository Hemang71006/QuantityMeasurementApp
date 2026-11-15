
package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC5 - Unit-to-Unit Conversion
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents immutable length measurements with conversion and equality operations.
 */

/**
 * Represents a length measurement using a numeric value and a unit.
 * All supported units are normalized to feet for conversion and comparison.
 */
public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    /**
     * Creates a length measurement.
     *
     * @param value numeric measurement value
     * @param unit measurement unit
     * @throws IllegalArgumentException if value is not finite or unit is null
     */
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

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Converts this measurement into the base unit feet.
     *
     * @return measurement expressed in feet
     */
    public double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    /**
     * Converts a value from one length unit to another.
     *
     * @param value measurement value
     * @param sourceUnit original unit
     * @param targetUnit desired unit
     * @return converted measurement value
     * @throws IllegalArgumentException if value is not finite or a unit is null
     * @throws ArithmeticException if the result cannot be represented as a finite double
     */
    public static double convert(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit
    ) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Conversion value must be a finite number"
            );
        }

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Source unit and target unit cannot be null"
            );
        }

        // Convert the source measurement into feet.
        double valueInFeet =
                value * sourceUnit.getConversionFactor();

        // Convert the base-unit value into the target unit.
        double convertedValue =
                valueInFeet / targetUnit.getConversionFactor();

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException(
                    "Conversion result exceeds the finite double range"
            );
        }

        return convertedValue;
    }

    /**
     * Converts this measurement into the requested unit.
     *
     * @param targetUnit desired unit
     * @return a new QuantityLength containing the converted measurement
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {

        double convertedValue =
                convert(value, unit, targetUnit);

        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Compares two measurements after converting them to feet.
     *
     * @param obj object to compare
     * @return true if both measurements represent the same length
     */
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

    /**
     * Generates a hash code using the base-unit value.
     *
     * @return hash code of the measurement
     */
    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    /**
     * Returns a readable representation of the measurement.
     *
     * @return measurement value and unit
     */
    @Override
    public String toString() {
        return value + " " + unit;
    }
}
