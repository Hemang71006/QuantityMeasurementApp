
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC7 - Addition with Target Unit Specification
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests addition with explicitly specified result units.
 */

class TargetUnitAdditionTest {

    private static final double EPSILON = 1e-5;

    // Tests addition with feet as the target unit.
    @Test
    void givenFeetAndInches_whenAddedInFeet_shouldReturnTwoFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests addition with inches as the target unit.
    @Test
    void givenFeetAndInches_whenAddedInInches_shouldReturnTwentyFourInches() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests addition with yards as the target unit.
    @Test
    void givenFeetAndInches_whenAddedInYards_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests addition with centimeters as the target unit.
    @Test
    void givenInches_whenAddedInCentimeters_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    // Tests when the target unit matches the first operand.
    @Test
    void givenYardsAndFeet_whenTargetIsFirstUnit_shouldReturnYards() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARDS
        );

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests when the target unit matches the second operand.
    @Test
    void givenYardsAndFeet_whenTargetIsSecondUnit_shouldReturnFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests commutativity with an explicitly specified target unit.
    @Test
    void givenMeasurements_whenAddedInReverseOrder_shouldReturnEqualResults() {
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 =
                QuantityLength.add(first, second, LengthUnit.YARDS);

        QuantityLength result2 =
                QuantityLength.add(second, first, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    // Tests addition with a zero measurement.
    @Test
    void givenZeroMeasurement_whenAddedInYards_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(5.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests addition with negative measurements.
    @Test
    void givenNegativeMeasurement_whenAddedInInches_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(36.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests null target unit validation.
    @Test
    void givenNullTargetUnit_whenAdded_shouldThrowException() {
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(first, second, null)
        );
    }

    // Tests null first operand validation.
    @Test
    void givenNullFirstOperand_whenAdded_shouldThrowException() {
        QuantityLength second = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(null, second, LengthUnit.FEET)
        );
    }

    // Tests null second operand validation.
    @Test
    void givenNullSecondOperand_whenAdded_shouldThrowException() {
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(first, null, LengthUnit.FEET)
        );
    }

    // Tests conversion from a large unit scale to a smaller unit scale.
    @Test
    void givenLargeMeasurements_whenAddedInInches_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1000.0, LengthUnit.FEET),
                new QuantityLength(500.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(18000.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests conversion from a smaller unit scale to a larger unit scale.
    @Test
    void givenInches_whenAddedInYards_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests addition using raw values and explicitly specified target unit.
    @Test
    void givenRawValues_whenAddedInTargetUnit_shouldReturnCorrectResult() {
        QuantityLength result = QuantityLength.add(
                1.0, LengthUnit.FEET,
                12.0, LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests that original measurements remain unchanged.
    @Test
    void givenMeasurements_whenAdded_shouldPreserveOriginalObjects() {
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(first, second, LengthUnit.YARDS);

        assertEquals(1.0, first.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, first.getUnit());

        assertEquals(12.0, second.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, second.getUnit());

        assertNotSame(first, result);
        assertNotSame(second, result);
    }

    // Tests all combinations of supported units.
    @Test
    void givenAllUnitCombinations_whenAdded_shouldPreservePhysicalResult() {
        LengthUnit[] units = LengthUnit.values();

        for (LengthUnit firstUnit : units) {
            for (LengthUnit secondUnit : units) {
                for (LengthUnit targetUnit : units) {

                    QuantityLength first =
                            new QuantityLength(2.0, firstUnit);

                    QuantityLength second =
                            new QuantityLength(3.0, secondUnit);

                    QuantityLength result =
                            QuantityLength.add(first, second, targetUnit);

                    double expectedInFeet =
                            first.convertToBaseUnit()
                                    + second.convertToBaseUnit();

                    double actualInFeet =
                            result.convertToBaseUnit();

                    assertEquals(
                            expectedInFeet,
                            actualInFeet,
                            EPSILON
                    );

                    assertEquals(targetUnit, result.getUnit());
                }
            }
        }
    }

    // Tests overflow handling.
    @Test
    void givenOverflowingMeasurements_whenAdded_shouldThrowException() {
        QuantityLength first =
                new QuantityLength(Double.MAX_VALUE, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(Double.MAX_VALUE, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> QuantityLength.add(first, second, LengthUnit.FEET)
        );
    }
}
