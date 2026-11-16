
package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Weight Compatibility Wrapper
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Preserves legacy weight operations using the generic Quantity class.
 */

public final class QuantityWeight {

    private static final double EPSILON = 1e-5;

    private final Quantity<WeightUnit> quantity;

    // Creates a weight measurement.
    public QuantityWeight(double value, WeightUnit unit) {
        quantity = new Quantity<>(value, unit);
    }

    // Creates a wrapper around an existing generic quantity.
    private QuantityWeight(Quantity<WeightUnit> quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        this.quantity = quantity;
    }

    // Returns the measurement value.
    public double getValue() {
        return quantity.getValue();
    }

    // Returns the measurement unit.
    public WeightUnit getUnit() {
        return quantity.getUnit();
    }

    // Converts this measurement into kilograms.
    private double convertToBaseUnit() {
        return quantity.convertToBaseUnit();
    }

    // Converts a value from one weight unit to another without rounding.
    private static double convert(
            double value,
            WeightUnit sourceUnit,
            WeightUnit targetUnit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Measurement value must be finite"
            );
        }

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Source and target units cannot be null"
            );
        }

        double baseValue = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    // Converts this measurement into the requested unit without rounding.
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double convertedValue = convert(
                getValue(),
                getUnit(),
                targetUnit
        );

        return new QuantityWeight(convertedValue, targetUnit);
    }

    // Adds another weight measurement and returns the result in this unit.
    public QuantityWeight add(QuantityWeight other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Second measurement cannot be null"
            );
        }

        return new QuantityWeight(quantity.add(other.quantity));
    }

    // Adds two weights and returns the result in the first unit.
    public static QuantityWeight add(
            QuantityWeight first,
            QuantityWeight second) {

        validateMeasurements(first, second);

        return new QuantityWeight(
                first.quantity.add(second.quantity)
        );
    }

    // Adds two weights and returns the result in the specified target unit.
    public static QuantityWeight add(
            QuantityWeight first,
            QuantityWeight second,
            WeightUnit targetUnit) {

        validateMeasurements(first, second);

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        return new QuantityWeight(
                first.quantity.add(second.quantity, targetUnit)
        );
    }

    // Validates both operands before addition.
    private static void validateMeasurements(
            QuantityWeight first,
            QuantityWeight second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Weight measurements cannot be null"
            );
        }
    }

    // Compares weight measurements using the legacy tolerance.
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityWeight other = (QuantityWeight) obj;

        return Math.abs(
                convertToBaseUnit() - other.convertToBaseUnit()
        ) < EPSILON;
    }

    // Uses a constant hash code to preserve the equals contract with tolerance.
    @Override
    public int hashCode() {
        return 1;
    }

    // Returns a readable representation of the measurement.
    @Override
    public String toString() {
        return "QuantityWeight(" + getValue() + ", " + getUnit() + ")";
    }
}
