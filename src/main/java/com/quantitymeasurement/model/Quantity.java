
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/*
 * Project Name : Quantity Measurement App
 * Module : UC13 - Centralized Arithmetic Logic
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Implements generic quantity operations using centralized arithmetic logic.
 */

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD((first, second) -> first + second),
        SUBTRACT((first, second) -> first - second),
        DIVIDE((first, second) -> {
            if (second == 0.0) {
                throw new ArithmeticException("Cannot divide by zero quantity");
            }
            return first / second;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        private double compute(double first, double second) {
            return operator.applyAsDouble(first, second);
        }
    }

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

        if (unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException(
                    "Target unit must belong to the same measurement category"
            );
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        validateFiniteResult(convertedValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultInBaseUnit =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        return createResult(resultInBaseUnit, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultInBaseUnit =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        return createResult(resultInBaseUnit, targetUnit);
    }

    /**
     * Divides this quantity by another quantity of the same category.
     *
     * @param other divisor quantity
     * @return dimensionless ratio
     */
    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        double result = operation.compute(
                firstBaseValue,
                secondBaseValue
        );

        validateFiniteResult(result);

        return result;
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException(
                    "Operation requires quantities from the same measurement category"
            );
        }

        validateFinite(value);
        validateFinite(other.value);

        if (targetUnitRequired) {
            validateTargetUnit(targetUnit);

            if (unit.getClass() != targetUnit.getClass()) {
                throw new IllegalArgumentException(
                        "Target unit must belong to the same measurement category"
                );
            }
        }
    }

    private Quantity<U> createResult(
            double resultInBaseUnit,
            U targetUnit) {

        double resultValue =
                targetUnit.convertFromBaseUnit(resultInBaseUnit);

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
            throw new IllegalArgumentException(
                    "Quantity value must be finite"
            );
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
