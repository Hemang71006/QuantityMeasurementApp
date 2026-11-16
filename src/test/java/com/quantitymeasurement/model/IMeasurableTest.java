
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Measurable Interface Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests common conversion behavior implemented by measurement units.
 */

class IMeasurableTest {

    // Tests that length units implement the common interface.
    @Test
    void givenLengthUnit_whenChecked_shouldImplementIMeasurable() {

        assertInstanceOf(IMeasurable.class, LengthUnit.FEET);
        assertInstanceOf(IMeasurable.class, LengthUnit.INCHES);
        assertInstanceOf(IMeasurable.class, LengthUnit.YARDS);
        assertInstanceOf(IMeasurable.class, LengthUnit.CENTIMETERS);
    }

    // Tests that weight units implement the common interface.
    @Test
    void givenWeightUnit_whenChecked_shouldImplementIMeasurable() {

        assertInstanceOf(IMeasurable.class, WeightUnit.KILOGRAM);
        assertInstanceOf(IMeasurable.class, WeightUnit.GRAM);
        assertInstanceOf(IMeasurable.class, WeightUnit.POUND);
    }

    // Tests the conversion factor of length units.
    @Test
    void givenLengthUnit_whenConversionFactorRequested_shouldReturnCorrectFactor() {

        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), 0.000001);
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 0.000001);
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), 0.000001);
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 0.000001);
    }

    // Tests the conversion factor of weight units.
    @Test
    void givenWeightUnit_whenConversionFactorRequested_shouldReturnCorrectFactor() {

        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), 0.000001);
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), 0.000001);
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor(), 0.000001);
    }

    // Tests conversion of length values into the base unit.
    @Test
    void givenLengthValue_whenConvertedToBaseUnit_shouldReturnFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                0.000001
        );

        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                0.000001
        );
    }

    // Tests conversion of weight values into the base unit.
    @Test
    void givenWeightValue_whenConvertedToBaseUnit_shouldReturnKilograms() {

        assertEquals(
                1.0,
                WeightUnit.GRAM.convertToBaseUnit(1000.0),
                0.000001
        );

        assertEquals(
                0.453592,
                WeightUnit.POUND.convertToBaseUnit(1.0),
                0.000001
        );
    }

    // Tests conversion from the base unit into length units.
    @Test
    void givenBaseLengthValue_whenConvertedFromBaseUnit_shouldReturnTargetValue() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                0.000001
        );

        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                0.000001
        );
    }

    // Tests conversion from the base unit into weight units.
    @Test
    void givenBaseWeightValue_whenConvertedFromBaseUnit_shouldReturnTargetValue() {

        assertEquals(
                1000.0,
                WeightUnit.GRAM.convertFromBaseUnit(1.0),
                0.000001
        );

        assertEquals(
                2.20462,
                WeightUnit.POUND.convertFromBaseUnit(1.0),
                0.0001
        );
    }

    // Tests unit names returned by the interface.
    @Test
    void givenMeasurementUnit_whenNameRequested_shouldReturnEnumName() {

        assertEquals("FEET", LengthUnit.FEET.getUnitName());
        assertEquals("KILOGRAM", WeightUnit.KILOGRAM.getUnitName());
    }

    // Tests rejection of non-finite values during base conversion.
    @Test
    void givenNonFiniteValue_whenConvertedToBaseUnit_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> LengthUnit.FEET.convertToBaseUnit(Double.NaN)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> WeightUnit.KILOGRAM.convertToBaseUnit(
                        Double.POSITIVE_INFINITY
                )
        );
    }
}
