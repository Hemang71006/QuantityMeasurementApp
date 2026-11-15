package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC3 - Generic Quantity Length
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests generic length comparison, unit conversion, validation, and object contracts.
 */

class QuantityLengthTest {

    // Tests equality between two identical feet measurements.
    @Test
    void givenSameFeetValues_whenCompared_shouldReturnTrue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(first, second);
    }

    // Tests equality between two identical inches measurements.
    @Test
    void givenSameInchesValues_whenCompared_shouldReturnTrue() {

        QuantityLength first =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(first, second);
    }

    // Tests conversion from feet to inches during comparison.
    @Test
    void givenOneFootAndTwelveInches_whenCompared_shouldReturnTrue() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    // Tests conversion from inches to feet during comparison.
    @Test
    void givenTwelveInchesAndOneFoot_whenCompared_shouldReturnTrue() {

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(inches, feet);
    }

    // Tests unequal feet measurements.
    @Test
    void givenDifferentFeetValues_whenCompared_shouldReturnFalse() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertNotEquals(first, second);
    }

    // Tests unequal inches measurements.
    @Test
    void givenDifferentInchesValues_whenCompared_shouldReturnFalse() {

        QuantityLength first =
                new QuantityLength(5.0, LengthUnit.INCHES);

        QuantityLength second =
                new QuantityLength(10.0, LengthUnit.INCHES);

        assertNotEquals(first, second);
    }

    // Tests unequal measurements across different units.
    @Test
    void givenDifferentFeetAndInches_whenCompared_shouldReturnFalse() {

        QuantityLength feet =
                new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertNotEquals(feet, inches);
    }

    // Tests comparison with null.
    @Test
    void givenNullObject_whenCompared_shouldReturnFalse() {

        QuantityLength measurement =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(null, measurement);
    }

    // Tests comparison with the same object reference.
    @Test
    void givenSameObjectReference_whenCompared_shouldReturnTrue() {

        QuantityLength measurement =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(measurement, measurement);
    }

    // Tests comparison with an object of a different class.
    @Test
    void givenNonQuantityLengthObject_whenCompared_shouldReturnFalse() {

        QuantityLength measurement =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(measurement, "1.0");
    }

    // Tests validation for a null unit.
    @Test
    void givenNullUnit_whenCreated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null)
        );
    }

    // Tests that a genuinely unsupported unit is rejected.
    @Test
    void givenUnsupportedUnit_whenRequested_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> LengthUnit.valueOf("MILES")
        );
    }

    // Tests validation for NaN values.
    @Test
    void givenNaNValue_whenCreated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET)
        );
    }

    // Tests validation for infinite values.
    @Test
    void givenInfiniteValue_whenCreated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET
                )
        );
    }

    // Tests that equal measurements produce equal hash codes.
    @Test
    void givenEqualMeasurements_whenHashCodeCalled_shouldReturnSameHashCode() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
        assertEquals(feet.hashCode(), inches.hashCode());
    }
}