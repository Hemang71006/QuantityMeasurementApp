package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC12 - Arithmetic Operations Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests generic quantity subtraction and division across measurement categories.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityArithmeticTest {

    private static final double EPSILON = 1e-9;

    @Test
    void givenSameUnitFeet_whenSubtracted_shouldReturnDifference() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void givenFeetAndInches_whenSubtracted_shouldReturnDifferenceInFeet() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(9.5, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void givenFeetAndInches_whenSubtractedWithInchesTarget_shouldReturnDifferenceInInches() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                first.subtract(second, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void givenLitreAndMillilitre_whenSubtracted_shouldReturnDifferenceInLitre() {
        Quantity<VolumeUnit> first = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.subtract(second);

        assertEquals(4.5, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenLitres_whenSubtractedWithMillilitreTarget_shouldReturnDifferenceInMillilitres() {
        Quantity<VolumeUnit> first = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                first.subtract(second, VolumeUnit.MILLILITRE);

        assertEquals(3000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void givenKilogramsAndGrams_whenSubtracted_shouldReturnDifferenceInKilograms() {
        Quantity<WeightUnit> first = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second = new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = first.subtract(second);

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenSmallerQuantity_whenSubtracted_shouldReturnNegativeResult() {
        Quantity<LengthUnit> first = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenEquivalentQuantities_whenSubtracted_shouldReturnZero() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void givenZeroQuantity_whenSubtracted_shouldPreserveValue() {
        Quantity<LengthUnit> first = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(0.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenNegativeQuantity_whenSubtracted_shouldHandleNegativeValue() {
        Quantity<LengthUnit> first = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(-2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    void givenQuantities_whenSubtracted_shouldNotModifyOriginalValues() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        first.subtract(second);

        assertEquals(10.0, first.getValue(), EPSILON);
        assertEquals(5.0, second.getValue(), EPSILON);
    }

    @Test
    void givenSubtraction_whenOperandsAreReversed_shouldReturnOppositeResult() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(5.0, first.subtract(second).getValue(), EPSILON);
        assertEquals(-5.0, second.subtract(first).getValue(), EPSILON);
    }

    @Test
    void givenSmallValues_whenSubtracted_shouldPreservePrecision() {
        Quantity<LengthUnit> first = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(0.0005, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.subtract(second);

        assertEquals(0.0005, result.getValue(), EPSILON);
    }

    @Test
    void givenLargeValues_whenSubtracted_shouldReturnCorrectDifference() {
        Quantity<WeightUnit> first = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second = new Quantity<>(5e5, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = first.subtract(second);

        assertEquals(5e5, result.getValue(), EPSILON);
    }

    @Test
    void givenNullOperand_whenSubtracted_shouldThrowException() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> first.subtract(null)
        );
    }

    @Test
    void givenNullTargetUnit_whenSubtracted_shouldThrowException() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> first.subtract(second, null)
        );
    }

    @Test
    void givenDifferentCategories_whenSubtracted_shouldThrowException() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight)
        );
    }

    @Test
    void givenSameUnitFeet_whenDivided_shouldReturnRatio() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, first.divide(second), EPSILON);
    }

    @Test
    void givenDifferentLengthUnits_whenDivided_shouldReturnRatio() {
        Quantity<LengthUnit> first = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> second = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, first.divide(second), EPSILON);
    }

    @Test
    void givenKilogramsAndGrams_whenDivided_shouldReturnRatio() {
        Quantity<WeightUnit> first = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second = new Quantity<>(2000.0, WeightUnit.GRAM);

        assertEquals(1.0, first.divide(second), EPSILON);
    }

    @Test
    void givenLitres_whenDivided_shouldReturnRatio() {
        Quantity<VolumeUnit> first = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(0.5, first.divide(second), EPSILON);
    }

    @Test
    void givenMillilitresAndLitres_whenDivided_shouldReturnRatio() {
        Quantity<VolumeUnit> first = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(1.0, first.divide(second), EPSILON);
    }

    @Test
    void givenEquivalentQuantities_whenDivided_shouldReturnOne() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(120.0, LengthUnit.INCHES);

        assertEquals(1.0, first.divide(second), EPSILON);
    }

    @Test
    void givenSmallerQuantity_whenDivided_shouldReturnRatioBelowOne() {
        Quantity<LengthUnit> first = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(0.5, first.divide(second), EPSILON);
    }

    @Test
    void givenDivision_whenOperandsAreReversed_shouldReturnReciprocalRatio() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(2.0, first.divide(second), EPSILON);
        assertEquals(0.5, second.divide(first), EPSILON);
    }

    @Test
    void givenZeroDivisor_whenDivided_shouldThrowArithmeticException() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> first.divide(second)
        );
    }

    @Test
    void givenNullOperand_whenDivided_shouldThrowException() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> first.divide(null)
        );
    }

    @Test
    void givenDifferentCategories_whenDivided_shouldThrowException() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide((Quantity) weight)
        );
    }

    @Test
    void givenLargeRatio_whenDivided_shouldReturnCorrectResult() {
        Quantity<WeightUnit> first = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1e6, first.divide(second), EPSILON);
    }

    @Test
    void givenSmallRatio_whenDivided_shouldReturnCorrectResult() {
        Quantity<WeightUnit> first = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> second = new Quantity<>(1e6, WeightUnit.KILOGRAM);

        assertEquals(1e-6, first.divide(second), EPSILON);
    }

    @Test
    void givenQuantities_whenDivided_shouldNotModifyOriginalValues() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        first.divide(second);

        assertEquals(10.0, first.getValue(), EPSILON);
        assertEquals(5.0, second.getValue(), EPSILON);
    }

    @Test
    void givenAdditionAndSubtraction_whenCombined_shouldReturnOriginalQuantity() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = first.add(second).subtract(second);

        assertEquals(first, result);
    }

    @Test
    void givenChainedSubtraction_whenExecuted_shouldReturnFinalDifference() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> third = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                first.subtract(second).subtract(third);

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    void givenSubtractionAndDivision_whenCombined_shouldWorkCorrectly() {
        Quantity<LengthUnit> first = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> third = new Quantity<>(2.0, LengthUnit.FEET);

        double result = first.subtract(second).divide(third);

        assertEquals(3.0, result, EPSILON);
    }

    @Test
    void givenAllCategories_whenSubtracted_shouldWorkGenerically() {
        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> volume =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(5.0,
                length.subtract(new Quantity<>(5.0, LengthUnit.FEET)).getValue(),
                EPSILON);

        assertEquals(5.0,
                weight.subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM)).getValue(),
                EPSILON);

        assertEquals(5.0,
                volume.subtract(new Quantity<>(5.0, VolumeUnit.LITRE)).getValue(),
                EPSILON);
    }

    @Test
    void givenAllCategories_whenDivided_shouldWorkGenerically() {
        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> volume =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(2.0,
                length.divide(new Quantity<>(5.0, LengthUnit.FEET)),
                EPSILON);

        assertEquals(2.0,
                weight.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)),
                EPSILON);

        assertEquals(2.0,
                volume.divide(new Quantity<>(5.0, VolumeUnit.LITRE)),
                EPSILON);
    }
}