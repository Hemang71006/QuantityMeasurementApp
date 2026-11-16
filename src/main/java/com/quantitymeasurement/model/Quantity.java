package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Quantity
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents an immutable quantity that supports generic unit conversion, equality, and addition.
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
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }

        validateTargetUnit(targetUnit);

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();
        double sumInBaseUnit = firstBaseValue + secondBaseValue;

        validateFiniteResult(sumInBaseUnit);

        double resultValue = targetUnit.convertFromBaseUnit(sumInBaseUnit);

        validateFiniteResult(resultValue);

        return new Quantity<>(resultValue, targetUnit);
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

        return Double.compare(convertToBaseUnit(), other.convertToBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), Double.hashCode(convertToBaseUnit()));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}