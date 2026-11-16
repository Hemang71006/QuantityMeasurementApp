
package com.quantitymeasurement.model;

/*
 * Project: Quantity Measurement Application
 * Description: Represents immutable weight measurements with equality, conversion, and addition operations.
 * Author: Hemang
 * Date: 15-11-2026
 */

public class QuantityWeight {

    private static final double EPSILON = 1e-5;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be finite");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Weight unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(convertedValue, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(this, other);
    }

    public static QuantityWeight add(
            QuantityWeight first,
            QuantityWeight second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Weight measurements cannot be null");
        }

        return add(first, second, first.unit);
    }

    public static QuantityWeight add(
            QuantityWeight first,
            QuantityWeight second,
            WeightUnit targetUnit) {

        if (first == null || second == null) {
            throw new IllegalArgumentException("Weight measurements cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double firstBaseValue = first.convertToBaseUnit();
        double secondBaseValue = second.convertToBaseUnit();

        double sumInBaseUnit = firstBaseValue + secondBaseValue;

        if (!Double.isFinite(sumInBaseUnit)) {
            throw new IllegalArgumentException("Weight addition result is not finite");
        }

        double result = targetUnit.convertFromBaseUnit(sumInBaseUnit);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityWeight other = (QuantityWeight) obj;

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        return Math.abs(firstBaseValue - secondBaseValue) < EPSILON;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "QuantityWeight(" + value + ", " + unit + ")";
    }
}
