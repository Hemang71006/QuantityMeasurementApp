
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC5 - Unit-to-Unit Conversion
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests conversions between supported length units and validates conversion behavior.
 */

class LengthConversionTest {

    private static final double EPSILON = 1e-6;

    // Tests feet to inches conversion.
    @Test
    void givenFeet_whenConvertedToInches_shouldReturnCorrectValue() {

        assertEquals(
                12.0,
                QuantityLength.convert(
                        1.0, LengthUnit.FEET, LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    // Tests inches to feet conversion.
    @Test
    void givenInches_whenConvertedToFeet_shouldReturnCorrectValue() {

        assertEquals(
                2.0,
                QuantityLength.convert(
                        24.0, LengthUnit.INCHES, LengthUnit.FEET
                ),
                EPSILON
        );
    }

    // Tests yards to inches conversion.
    @Test
    void givenYards_whenConvertedToInches_shouldReturnCorrectValue() {

        assertEquals(
                36.0,
                QuantityLength.convert(
                        1.0, LengthUnit.YARDS, LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    // Tests inches to yards conversion.
    @Test
    void givenInches_whenConvertedToYards_shouldReturnCorrectValue() {

        assertEquals(
                2.0,
                QuantityLength.convert(
                        72.0, LengthUnit.INCHES, LengthUnit.YARDS
                ),
                EPSILON
        );
    }

    // Tests centimeters to inches conversion.
    @Test
    void givenCentimeters_whenConvertedToInches_shouldReturnCorrectValue() {

        assertEquals(
                1.0,
                QuantityLength.convert(
                        2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    // Tests feet to yards conversion.
    @Test
    void givenFeet_whenConvertedToYards_shouldReturnCorrectValue() {

        assertEquals(
                2.0,
                QuantityLength.convert(
                        6.0, LengthUnit.FEET, LengthUnit.YARDS
                ),
                EPSILON
        );
    }

    // Tests centimeters to feet conversion.
    @Test
    void givenCentimeters_whenConvertedToFeet_shouldReturnCorrectValue() {

        assertEquals(
                0.0328084167,
                QuantityLength.convert(
                        1.0, LengthUnit.CENTIMETERS, LengthUnit.FEET
                ),
                EPSILON
        );
    }

    // Tests round-trip conversion.
    @Test
    void givenMeasurement_whenConvertedToAnotherUnitAndBack_shouldPreserveValue() {

        double originalValue = 5.0;

        double convertedValue = QuantityLength.convert(
                originalValue, LengthUnit.FEET, LengthUnit.INCHES
        );

        double returnedValue = QuantityLength.convert(
                convertedValue, LengthUnit.INCHES, LengthUnit.FEET
        );

        assertEquals(originalValue, returnedValue, EPSILON);
    }

    // Tests conversion of zero.
    @Test
    void givenZeroValue_whenConverted_shouldReturnZero() {

        assertEquals(
                0.0,
                QuantityLength.convert(
                        0.0, LengthUnit.FEET, LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    // Tests conversion of a negative measurement.
    @Test
    void givenNegativeValue_whenConverted_shouldPreserveNegativeSign() {

        assertEquals(
                -12.0,
                QuantityLength.convert(
                        -1.0, LengthUnit.FEET, LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    // Tests conversion from a unit to itself.
    @Test
    void givenSameSourceAndTargetUnit_whenConverted_shouldReturnOriginalValue() {

        assertEquals(
                5.0,
                QuantityLength.convert(
                        5.0, LengthUnit.FEET, LengthUnit.FEET
                ),
                EPSILON
        );
    }

    // Tests instance-based conversion.
    @Test
    void givenQuantityLength_whenConvertedToAnotherUnit_shouldReturnNewMeasurement() {

        QuantityLength original =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength converted =
                original.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, converted.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
        assertNotSame(original, converted);
    }

    // Tests conversion with a null source unit.
    @Test
    void givenNullSourceUnit_whenConverted_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        1.0, null, LengthUnit.INCHES
                )
        );
    }

    // Tests conversion with a null target unit.
    @Test
    void givenNullTargetUnit_whenConverted_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        1.0, LengthUnit.FEET, null
                )
        );
    }

    // Tests conversion with NaN.
    @Test
    void givenNaNValue_whenConverted_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        Double.NaN, LengthUnit.FEET, LengthUnit.INCHES
                )
        );
    }

    // Tests conversion with positive infinity.
    @Test
    void givenInfiniteValue_whenConverted_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.convert(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );
    }

    // Tests conversion with a very large value that overflows the result.
    @Test
    void givenOverflowingConversion_whenConverted_shouldThrowException() {

        assertThrows(
                ArithmeticException.class,
                () -> QuantityLength.convert(
                        Double.MAX_VALUE,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );
    }

    // Tests the readable string representation.
    @Test
    void givenQuantityLength_whenToStringCalled_shouldReturnReadableValue() {

        QuantityLength measurement =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals("1.0 FEET", measurement.toString());
    }
}
