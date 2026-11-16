
package com.quantitymeasurement.model;

/*
 * Project: Quantity Measurement Application
 * Description: Tests conversion responsibilities of WeightUnit.
 * Author: Hemang
 * Date: 15-11-2026
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightUnitTest {

    private static final double EPSILON = 1e-6;

    @Test
    void givenKilograms_whenConvertedToBaseUnit_shouldReturnSameValue() {
        assertEquals(1.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void givenGrams_whenConvertedToBaseUnit_shouldReturnKilograms() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    void givenPounds_whenConvertedToBaseUnit_shouldReturnKilograms() {
        assertEquals(0.907184, WeightUnit.POUND.convertToBaseUnit(2.0), EPSILON);
    }

    @Test
    void givenKilograms_whenConvertedFromBaseUnitToGrams_shouldReturnGrams() {
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void givenKilograms_whenConvertedFromBaseUnitToPounds_shouldReturnPounds() {
        assertEquals(2.204624420, WeightUnit.POUND.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void givenUnit_whenGetConversionFactor_shouldReturnCorrectFactor() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor());
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor());
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor());
    }

    @Test
    void givenNaN_whenConvertedToBaseUnit_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> WeightUnit.KILOGRAM.convertToBaseUnit(Double.NaN));
    }

    @Test
    void givenInfiniteValue_whenConvertedFromBaseUnit_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> WeightUnit.GRAM.convertFromBaseUnit(Double.POSITIVE_INFINITY));
    }
}
