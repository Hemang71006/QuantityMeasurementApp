
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Quantity Class
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents any measurable quantity using a generic unit type.
 */

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // Creates an immutable quantity with a value and its unit.
    public Quantity(double value, U unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Measurement value must be finite"
            );
        }

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Measurement unit cannot be null"
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
    public U getUnit() {
        return unit;
    }

    // Converts this quantity into its category's base unit.
    public double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Converts this quantity into the requested unit.
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue = convertToBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        double roundedValue =
                Math.round(convertedValue * 100.0) / 100.0;

        if (!Double.isFinite(roundedValue)) {
            throw new ArithmeticException(
                    "Converted quantity exceeds the finite double range"
            );
        }

        return new Quantity<>(roundedValue, targetUnit);
    }

    // Adds another quantity and returns the result in this quantity's unit.
    public Quantity<U> add(Quantity<U> other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        return add(other, unit);
    }

    // Adds two quantities and returns the result in the specified target unit.
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        double sumInBaseUnit = firstBaseValue + secondBaseValue;

        if (!Double.isFinite(sumInBaseUnit)) {
            throw new ArithmeticException(
                    "Addition result exceeds the finite double range"
            );
        }

        double resultValue =
                targetUnit.convertFromBaseUnit(sumInBaseUnit);

        if (!Double.isFinite(resultValue)) {
            throw new ArithmeticException(
                    "Addition result exceeds the finite double range"
            );
        }

        return new Quantity<>(resultValue, targetUnit);
    }

    // Compares quantities only when their unit categories match.
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        return Double.compare(firstBaseValue, secondBaseValue) == 0;
    }

    // Generates a hash code based on the base-unit value and category.
    @Override
    public int hashCode() {

        return 31 * unit.getClass().hashCode()
                + Double.hashCode(convertToBaseUnit());
    }

    // Returns a readable representation of the quantity.
    @Override
    public String toString() {

        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
