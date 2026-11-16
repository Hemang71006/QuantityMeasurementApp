
package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC8 - QuantityLength Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents immutable length measurements with comparison, conversion, and addition operations.
 */

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    // Creates a length measurement.
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

    // Delegates conversion to the unit enum.
    public double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Converts a value from one length unit to another.
    public static double convert(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Measurement value must be finite"
            );
        }

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Units cannot be null"
            );
        }

        double baseValue = sourceUnit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(baseValue);
    }

    // Converts this measurement into the requested unit.
    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double convertedValue =
                targetUnit.convertFromBaseUnit(convertToBaseUnit());

        return new QuantityLength(convertedValue, targetUnit);
    }

    // Adds another measurement to this measurement.
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Second measurement cannot be null"
            );
        }

        return add(this, other);
    }

    // Adds two measurements and returns the result in the first unit.
    public static QuantityLength add(
            QuantityLength first,
            QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Length measurements cannot be null"
            );
        }

        double firstInFeet = first.convertToBaseUnit();
        double secondInFeet = second.convertToBaseUnit();

        double sumInFeet = firstInFeet + secondInFeet;

        return createFromBaseUnit(sumInFeet, first.unit);
    }

    // Adds raw measurement values and returns the result in the first unit.
    public static QuantityLength add(
            double firstValue,
            LengthUnit firstUnit,
            double secondValue,
            LengthUnit secondUnit) {

        QuantityLength first =
                new QuantityLength(firstValue, firstUnit);

        QuantityLength second =
                new QuantityLength(secondValue, secondUnit);

        return add(first, second);
    }

    // Adds two measurements and returns the result in the specified target unit.
    public static QuantityLength add(
            QuantityLength first,
            QuantityLength second,
            LengthUnit targetUnit) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Length measurements cannot be null"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double firstInFeet = first.convertToBaseUnit();
        double secondInFeet = second.convertToBaseUnit();

        double sumInFeet = firstInFeet + secondInFeet;

        return createFromBaseUnit(sumInFeet, targetUnit);
    }

    // Adds raw values and returns the result in the specified target unit.
    public static QuantityLength add(
            double firstValue,
            LengthUnit firstUnit,
            double secondValue,
            LengthUnit secondUnit,
            LengthUnit targetUnit) {

        QuantityLength first =
                new QuantityLength(firstValue, firstUnit);

        QuantityLength second =
                new QuantityLength(secondValue, secondUnit);

        return add(first, second, targetUnit);
    }

    // Creates a new measurement by converting a base unit value into the target unit.
    private static QuantityLength createFromBaseUnit(
            double baseValue,
            LengthUnit targetUnit) {

        if (!Double.isFinite(baseValue)) {
            throw new ArithmeticException(
                    "Addition result exceeds the finite double range"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    // Compares two measurements after converting them to feet.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        double firstValue = convertToBaseUnit();
        double secondValue = other.convertToBaseUnit();

        return Math.abs(firstValue - secondValue) < 1e-9;
    }

    // Generates a hash code using the base unit value.
    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    // Returns a readable representation of the measurement.
    @Override
    public String toString() {
        return value + " " + unit;
    }
}
