
package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Length Compatibility Wrapper
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Preserves legacy length operations using the generic Quantity class.
 */

public final class QuantityLength {

    private static final double EPSILON = 1e-9;

    private final Quantity<LengthUnit> quantity;

    // Creates a length measurement.
    public QuantityLength(double value, LengthUnit unit) {
        quantity = new Quantity<>(value, unit);
    }

    // Creates a wrapper around an existing generic quantity.
    private QuantityLength(Quantity<LengthUnit> quantity) {

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
    public LengthUnit getUnit() {
        return quantity.getUnit();
    }

    // Converts this measurement into the base unit.
    public double convertToBaseUnit() {
        return quantity.convertToBaseUnit();
    }

    // Converts a value from one length unit to another without rounding.
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
                    "Source and target units cannot be null"
            );
        }

        double baseValue = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    // Converts this measurement into the requested unit without rounding.
    public QuantityLength convertTo(LengthUnit targetUnit) {

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

        return new QuantityLength(convertedValue, targetUnit);
    }

    // Adds another measurement and returns the result in this unit.
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Second measurement cannot be null"
            );
        }

        return new QuantityLength(quantity.add(other.quantity));
    }

    // Adds two measurements and returns the result in the first unit.
    public static QuantityLength add(
            QuantityLength first,
            QuantityLength second) {

        validateMeasurements(first, second);

        return new QuantityLength(
                first.quantity.add(second.quantity)
        );
    }

    // Adds raw values and returns the result in the first unit.
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

    // Adds two measurements and returns the result in the target unit.
    public static QuantityLength add(
            QuantityLength first,
            QuantityLength second,
            LengthUnit targetUnit) {

        validateMeasurements(first, second);

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        return new QuantityLength(
                first.quantity.add(second.quantity, targetUnit)
        );
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

    // Validates both operands before addition.
    private static void validateMeasurements(
            QuantityLength first,
            QuantityLength second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Length measurements cannot be null"
            );
        }
    }

    // Compares length measurements using the legacy tolerance.
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

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
        return getValue() + " " + getUnit();
    }
}
