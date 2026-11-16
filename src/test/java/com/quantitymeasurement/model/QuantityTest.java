
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Quantity Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests generic quantity operations for multiple measurement categories.
 */

class QuantityTest {

    // Tests equality between equivalent length quantities.
    @Test
    void givenEquivalentLengthQuantities_whenCompared_shouldReturnTrue() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    // Tests equality between equivalent weight quantities.
    @Test
    void givenEquivalentWeightQuantities_whenCompared_shouldReturnTrue() {

        Quantity<WeightUnit> kilograms =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kilograms, grams);
    }

    // Tests that different measurement categories are not equal.
    @Test
    void givenDifferentMeasurementCategories_whenCompared_shouldReturnFalse() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // Tests conversion between length units.
    @Test
    void givenLengthQuantity_whenConverted_shouldReturnTargetUnitQuantity() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests conversion between weight units.
    @Test
    void givenWeightQuantity_whenConverted_shouldReturnTargetUnitQuantity() {

        Quantity<WeightUnit> kilograms =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kilograms.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Tests addition using the first operand's unit.
    @Test
    void givenLengthQuantities_whenAdded_shouldReturnSumInFirstUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests addition using an explicitly selected target unit.
    @Test
    void givenWeightQuantities_whenAddedWithTargetUnit_shouldReturnTargetUnitSum() {

        Quantity<WeightUnit> kilograms =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kilograms.add(grams, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Tests hash code consistency for equal quantities.
    @Test
    void givenEqualQuantities_whenHashCodeCalled_shouldReturnSameHashCode() {

        Quantity<LengthUnit> first =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    // Tests rejection of a null unit.
    @Test
    void givenNullUnit_whenQuantityCreated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<LengthUnit>(1.0, null)
        );
    }

    // Tests rejection of a non-finite measurement value.
    @Test
    void givenNonFiniteValue_whenQuantityCreated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET)
        );
    }

    // Tests rejection of a null quantity during addition.
    @Test
    void givenNullQuantity_whenAdded_shouldThrowException() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> feet.add(null)
        );
    }

    // Tests rejection of a null target unit during conversion.
    @Test
    void givenNullTargetUnit_whenConverted_shouldThrowException() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> feet.convertTo(null)
        );
    }
}
