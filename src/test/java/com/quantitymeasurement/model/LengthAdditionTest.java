
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC6 - Addition of Two Length Units
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests same-unit and cross-unit length addition operations.
 */

class LengthAdditionTest {

    private static final double EPSILON = 1e-6;

    // Tests addition of two feet measurements.
    @Test
    void givenFeetMeasurements_whenAdded_shouldReturnSumInFeet() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests addition of two inches measurements.
    @Test
    void givenInchesMeasurements_whenAdded_shouldReturnSumInInches() {

        QuantityLength first =
                new QuantityLength(6.0, LengthUnit.INCHES);

        QuantityLength second =
                new QuantityLength(6.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests addition of feet and inches.
    @Test
    void givenFeetAndInches_whenAdded_shouldReturnSumInFeet() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests addition when inches are the first operand.
    @Test
    void givenInchesAndFeet_whenAdded_shouldReturnSumInInches() {

        QuantityLength first =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests addition of yards and feet.
    @Test
    void givenYardsAndFeet_whenAdded_shouldReturnSumInYards() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength second =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests addition of centimeters and inches.
    @Test
    void givenCentimetersAndInches_whenAdded_shouldReturnSumInCentimeters() {

        QuantityLength first =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(5.08, result.getValue(), 1e-5);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    // Tests commutativity by comparing the physical measurements.
    @Test
    void givenTwoMeasurements_whenAddedInReverseOrder_shouldProduceEqualLengths() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength firstResult =
                QuantityLength.add(first, second);

        QuantityLength secondResult =
                QuantityLength.add(second, first);

        assertEquals(firstResult, secondResult);
    }

    // Tests addition with zero.
    @Test
    void givenZeroMeasurement_whenAdded_shouldPreserveOriginalLength() {

        QuantityLength first =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests addition of negative measurements.
    @Test
    void givenNegativeMeasurement_whenAdded_shouldReturnCorrectSum() {

        QuantityLength first =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // Tests addition with a null second operand.
    @Test
    void givenNullSecondOperand_whenAdded_shouldThrowException() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(first, null)
        );
    }

    // Tests addition with a null first operand.
    @Test
    void givenNullFirstOperand_whenAdded_shouldThrowException() {

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(null, second)
        );
    }

    // Tests addition using the instance method.
    @Test
    void givenQuantityLength_whenInstanceAddCalled_shouldReturnNewMeasurement() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = first.add(second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
        assertNotSame(first, result);
    }

    // Tests addition using raw values and units.
    @Test
    void givenRawValues_whenAdded_shouldReturnQuantityInFirstUnit() {

        QuantityLength result = QuantityLength.add(
                1.0, LengthUnit.YARDS,
                3.0, LengthUnit.FEET
        );

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // Tests addition with large values.
    @Test
    void givenLargeMeasurements_whenAdded_shouldReturnCorrectSum() {

        QuantityLength first =
                new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    // Tests addition with small values.
    @Test
    void givenSmallMeasurements_whenAdded_shouldReturnCorrectSum() {

        QuantityLength first =
                new QuantityLength(0.001, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(first, second);

        assertEquals(0.003, result.getValue(), EPSILON);
    }

    // Tests that the original measurements remain unchanged.
    @Test
    void givenTwoMeasurements_whenAdded_shouldNotModifyOriginalObjects() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength.add(first, second);

        assertEquals(1.0, first.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, first.getUnit());

        assertEquals(12.0, second.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, second.getUnit());
    }

    // Tests addition overflow.
    @Test
    void givenOverflowingMeasurements_whenAdded_shouldThrowException() {

        QuantityLength first =
                new QuantityLength(Double.MAX_VALUE, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(Double.MAX_VALUE, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> QuantityLength.add(first, second)
        );
    }
}
