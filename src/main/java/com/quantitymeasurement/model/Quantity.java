package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC12 - Subtraction and Division
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents an immutable generic quantity supporting conversion and arithmetic operations.
 */

import com.quantitymeasurement.interfaces.IMeasurable;
import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        validateFinite(value);

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        validateTargetUnit(targetUnit);

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        validateFiniteResult(convertedValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }

        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateCompatible(other);
        validateTargetUnit(targetUnit);

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();
        double sumInBaseUnit = firstBaseValue + secondBaseValue;

        validateFiniteResult(sumInBaseUnit);

        double resultValue = targetUnit.convertFromBaseUnit(sumInBaseUnit);

        validateFiniteResult(resultValue);

        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Subtracts another quantity from this quantity.
     * The result is expressed in this quantity's unit.
     *
     * @param other quantity to subtract
     * @return a new quantity containing the difference
     * @throws IllegalArgumentException if other is null or belongs to another category
     * @throws ArithmeticException if the calculation overflows
     */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    /**
     * Subtracts another quantity and expresses the result in the target unit.
     * Both quantities are converted to their common base unit before subtraction.
     *
     * @param other quantity to subtract
     * @param targetUnit unit in which the result should be expressed
     * @return a new quantity containing the difference in the target unit
     * @throws IllegalArgumentException if an argument is null or categories differ
     * @throws ArithmeticException if the calculation overflows
     */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateCompatible(other);
        validateTargetUnit(targetUnit);

        if (unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException(
                    "Target unit must belong to the same measurement category"
            );
        }

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        double differenceInBaseUnit = firstBaseValue - secondBaseValue;

        validateFiniteResult(differenceInBaseUnit);

        double resultValue =
                targetUnit.convertFromBaseUnit(differenceInBaseUnit);

        validateFiniteResult(resultValue);

        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Divides this quantity by another quantity of the same category.
     * The result is a dimensionless ratio.
     *
     * @param other divisor quantity
     * @return the ratio of this quantity to the other quantity
     * @throws IllegalArgumentException if other is null or belongs to another category
     * @throws ArithmeticException if the divisor is zero or the result overflows
     */
    public double divide(Quantity<U> other) {
        validateCompatible(other);

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        if (secondBaseValue == 0.0) {
            throw new ArithmeticException("Cannot divide by zero quantity");
        }

        double result = firstBaseValue / secondBaseValue;

        validateFiniteResult(result);

        return result;
    }

    private void validateCompatible(Quantity<?> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException(
                    "Operation requires quantities from the same measurement category"
            );
        }
    }

    private static void validateTargetUnit(IMeasurable targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Quantity value must be finite");
        }
    }

    private static void validateFiniteResult(double value) {
        if (!Double.isFinite(value)) {
            throw new ArithmeticException("Quantity calculation overflow");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) object;

        if (unit.getClass() != other.unit.getClass()) {
            return false;
        }

        return Double.compare(
                convertToBaseUnit(),
                other.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                unit.getClass(),
                Double.hashCode(convertToBaseUnit())
        );
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}