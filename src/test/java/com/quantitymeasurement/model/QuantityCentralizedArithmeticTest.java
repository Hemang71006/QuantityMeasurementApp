
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC13 - Centralized Arithmetic Logic
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests centralized arithmetic operations for generic quantities.
 */

class QuantityCentralizedArithmeticTest {

    private static final double DELTA = 1e-9;

    @Test
    void givenLengthQuantities_whenAdded_shouldReturnSum() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = first.add(second);

        assertEquals(6, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void givenLengthQuantities_whenSubtracted_shouldReturnDifference() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(4, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void givenLengthQuantities_whenSubtractedWithTargetUnit_shouldConvertResult() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                first.subtract(second, LengthUnit.INCHES);

        assertEquals(48, result.getValue(), DELTA);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void givenLengthQuantities_whenDivided_shouldReturnDimensionlessRatio() {
        Quantity<LengthUnit> first =
                new Quantity<>(6, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(2, LengthUnit.FEET);

        assertEquals(3, first.divide(second), DELTA);
    }

    @Test
    void givenWeightQuantities_whenAdded_shouldReturnSum() {
        Quantity<WeightUnit> first =
                new Quantity<>(2, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second =
                new Quantity<>(500, WeightUnit.GRAM);

        Quantity<WeightUnit> result = first.add(second);

        assertEquals(2.5, result.getValue(), DELTA);
    }

    @Test
    void givenVolumeQuantities_whenAdded_shouldReturnSum() {
        Quantity<VolumeUnit> first =
                new Quantity<>(2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second =
                new Quantity<>(500, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(2.5, result.getValue(), DELTA);
    }

    @Test
    void givenVolumeQuantities_whenSubtracted_shouldReturnDifference() {
        Quantity<VolumeUnit> first =
                new Quantity<>(3, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second =
                new Quantity<>(500, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.subtract(second);

        assertEquals(2.5, result.getValue(), DELTA);
    }

    @Test
    void givenWeightQuantities_whenDivided_shouldReturnRatio() {
        Quantity<WeightUnit> first =
                new Quantity<>(4, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second =
                new Quantity<>(2, WeightUnit.KILOGRAM);

        assertEquals(2, first.divide(second), DELTA);
    }

    @Test
    void givenVolumeQuantities_whenDivided_shouldReturnRatio() {
        Quantity<VolumeUnit> first =
                new Quantity<>(6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second =
                new Quantity<>(2, VolumeUnit.LITRE);

        assertEquals(3, first.divide(second), DELTA);
    }

    @Test
    void givenNullOperand_whenAdded_shouldThrowException() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(5, LengthUnit.FEET);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quantity.add(null)
        );

        assertEquals("Quantity cannot be null", exception.getMessage());
    }

    @Test
    void givenNullOperand_whenSubtracted_shouldThrowException() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(5, LengthUnit.FEET);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quantity.subtract(null)
        );

        assertEquals("Quantity cannot be null", exception.getMessage());
    }

    @Test
    void givenNullOperand_whenDivided_shouldThrowException() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(5, LengthUnit.FEET);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quantity.divide(null)
        );

        assertEquals("Quantity cannot be null", exception.getMessage());
    }

    @Test
    void givenNullTargetUnit_whenAdded_shouldThrowException() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(2, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> first.add(second, null)
        );
    }

    @Test
    void givenNullTargetUnit_whenSubtracted_shouldThrowException() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(2, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> first.subtract(second, null)
        );
    }

    @Test
    void givenZeroQuantity_whenDivided_shouldThrowException() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(0, LengthUnit.INCHES);

        assertThrows(
                ArithmeticException.class,
                () -> first.divide(second)
        );
    }

    @Test
    void givenArithmeticOperation_whenExecuted_shouldNotModifyOriginalQuantities() {
        Quantity<LengthUnit> first =
                new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(2, LengthUnit.FEET);

        first.add(second);
        first.subtract(second);

        assertEquals(5, first.getValue(), DELTA);
        assertEquals(2, second.getValue(), DELTA);
    }

    @Test
    void givenSmallValues_whenSubtracted_shouldPreservePrecision() {
        Quantity<LengthUnit> first =
                new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(0.0005, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(0.0005, result.getValue(), DELTA);
    }
}
