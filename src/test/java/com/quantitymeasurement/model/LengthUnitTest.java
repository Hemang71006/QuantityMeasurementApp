
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC8 - LengthUnit Conversion Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests standalone LengthUnit conversion responsibilities.
 */

class LengthUnitTest {

    private static final double EPSILON = 1e-5;

    // Tests feet to base unit.
    @Test
    void givenFeet_whenConvertedToBaseUnit_shouldReturnSameValue() {
        assertEquals(
                5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON
        );
    }

    // Tests inches to base unit.
    @Test
    void givenInches_whenConvertedToBaseUnit_shouldReturnFeet() {
        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON
        );
    }

    // Tests yards to base unit.
    @Test
    void givenYards_whenConvertedToBaseUnit_shouldReturnFeet() {
        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON
        );
    }

    // Tests centimeters to base unit.
    @Test
    void givenCentimeters_whenConvertedToBaseUnit_shouldReturnFeet() {
        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON
        );
    }

    // Tests base unit conversion into feet.
    @Test
    void givenFeetBaseValue_whenConvertedToFeet_shouldReturnSameValue() {
        assertEquals(
                2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON
        );
    }

    // Tests base unit conversion into inches.
    @Test
    void givenFeetBaseValue_whenConvertedToInches_shouldReturnInches() {
        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    // Tests base unit conversion into yards.
    @Test
    void givenFeetBaseValue_whenConvertedToYards_shouldReturnYards() {
        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON
        );
    }

    // Tests base unit conversion into centimeters.
    @Test
    void givenFeetBaseValue_whenConvertedToCentimeters_shouldReturnCentimeters() {
        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    // Tests null-independent unit conversion behavior for invalid input.
    @Test
    void givenNaN_whenConvertedToBaseUnit_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> LengthUnit.FEET.convertToBaseUnit(Double.NaN)
        );
    }

    // Tests infinite input validation.
    @Test
    void givenInfiniteValue_whenConvertedFromBaseUnit_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> LengthUnit.INCHES.convertFromBaseUnit(
                        Double.POSITIVE_INFINITY
                )
        );
    }
}
