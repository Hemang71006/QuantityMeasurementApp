package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC4 - Extended Unit Support
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests extended length units and cross-unit equality.
 */

class ExtendedLengthUnitTest {

    // Tests equality between identical yard measurements.
    @Test
    void givenSameYardValues_whenCompared_shouldReturnTrue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(first, second);
    }

    // Tests inequality between different yard measurements.
    @Test
    void givenDifferentYardValues_whenCompared_shouldReturnFalse() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.YARDS);

        assertNotEquals(first, second);
    }

    // Tests conversion from yards to feet.
    @Test
    void givenOneYardAndThreeFeet_whenCompared_shouldReturnTrue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(yard, feet);
    }

    // Tests reverse conversion from feet to yards.
    @Test
    void givenThreeFeetAndOneYard_whenCompared_shouldReturnTrue() {

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(feet, yard);
    }

    // Tests conversion from yards to inches.
    @Test
    void givenOneYardAndThirtySixInches_whenCompared_shouldReturnTrue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        assertEquals(yard, inches);
    }

    // Tests reverse conversion from inches to yards.
    @Test
    void givenThirtySixInchesAndOneYard_whenCompared_shouldReturnTrue() {

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(inches, yard);
    }

    // Tests unequal yard and feet measurements.
    @Test
    void givenOneYardAndTwoFeet_whenCompared_shouldReturnFalse() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertNotEquals(yard, feet);
    }

    // Tests equality between identical centimeter measurements.
    @Test
    void givenSameCentimeterValues_whenCompared_shouldReturnTrue() {

        QuantityLength first =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        assertEquals(first, second);
    }

    // Tests inequality between different centimeter measurements.
    @Test
    void givenDifferentCentimeterValues_whenCompared_shouldReturnFalse() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        assertNotEquals(first, second);
    }

    @Test
    void givenEquivalentInchesAndOneCentimeter_whenCompared_shouldReturnTrue() {
        QuantityLength inches = new QuantityLength(1.0 / 2.54, LengthUnit.INCHES);
        QuantityLength centimeters = new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertEquals(inches, centimeters);
    }

    @Test
    void givenOneCentimeterAndEquivalentInches_whenCompared_shouldReturnTrue() {
        QuantityLength centimeters = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength inches = new QuantityLength(1.0 / 2.54, LengthUnit.INCHES);

        assertEquals(centimeters, inches);
    }

    // Tests that one centimeter is not equal to one foot.
    @Test
    void givenOneCentimeterAndOneFoot_whenCompared_shouldReturnFalse() {

        QuantityLength centimeters =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(centimeters, feet);
    }

    // Tests transitive equality across yards, feet, and inches.
    @Test
    void givenEquivalentMeasurementsInThreeUnits_whenCompared_shouldReturnTrue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    // Tests comparison with the same yard object reference.
    @Test
    void givenSameYardReference_whenCompared_shouldReturnTrue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(yard, yard);
    }

    // Tests comparison of a yard measurement with null.
    @Test
    void givenYardMeasurementAndNull_whenCompared_shouldReturnFalse() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertNotEquals(null, yard);
    }

    // Tests comparison with the same centimeter object reference.
    @Test
    void givenSameCentimeterReference_whenCompared_shouldReturnTrue() {

        QuantityLength centimeters =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertEquals(centimeters, centimeters);
    }

    // Tests comparison of a centimeter measurement with null.
    @Test
    void givenCentimeterMeasurementAndNull_whenCompared_shouldReturnFalse() {

        QuantityLength centimeters =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertNotEquals(null, centimeters);
    }

    // Tests rejection of a null unit.
    @Test
    void givenNullUnit_whenCreatingYardMeasurement_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null)
        );
    }

    // Tests rejection of unsupported units.
    @Test
    void givenUnsupportedUnit_whenRequested_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> LengthUnit.valueOf("METERS")
        );
    }

    // Tests hash code consistency across equivalent units.
    @Test
    void givenEquivalentYardAndFeet_whenHashCodeCalled_shouldReturnSameHashCode() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(yard, feet);
        assertEquals(yard.hashCode(), feet.hashCode());
    }
}