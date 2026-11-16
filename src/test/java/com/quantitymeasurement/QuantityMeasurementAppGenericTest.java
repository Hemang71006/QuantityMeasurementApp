
package com.quantitymeasurement;

import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Application Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests generic application operations across measurement categories.
 */

class QuantityMeasurementAppGenericTest {

    // Tests generic equality for length measurements.
    @Test
    void givenEquivalentLengthQuantities_whenEqualityDemonstrated_shouldReturnTrue() {

        Quantity<LengthUnit> first =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(
                QuantityMeasurementApp.demonstrateEquality(first, second)
        );
    }

    // Tests generic equality for weight measurements.
    @Test
    void givenEquivalentWeightQuantities_whenEqualityDemonstrated_shouldReturnTrue() {

        Quantity<WeightUnit> first =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(
                QuantityMeasurementApp.demonstrateEquality(first, second)
        );
    }

    // Tests that different categories are not equal.
    @Test
    void givenDifferentCategories_whenEqualityDemonstrated_shouldReturnFalse() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(
                QuantityMeasurementApp.demonstrateEquality(length, weight)
        );
    }

    // Tests generic conversion for length.
    @Test
    void givenLengthQuantity_whenConversionDemonstrated_shouldReturnConvertedQuantity() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateConversion(
                        feet,
                        LengthUnit.INCHES
                );

        assertEquals(12.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Tests generic conversion for weight.
    @Test
    void givenWeightQuantity_whenConversionDemonstrated_shouldReturnConvertedQuantity() {

        Quantity<WeightUnit> kilograms =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateConversion(
                        kilograms,
                        WeightUnit.GRAM
                );

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Tests generic addition for length.
    @Test
    void givenLengthQuantities_whenAdditionDemonstrated_shouldReturnSum() {

        Quantity<LengthUnit> first =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateAddition(first, second);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Tests generic addition for weight with a target unit.
    @Test
    void givenWeightQuantities_whenTargetAdditionDemonstrated_shouldReturnTargetUnitSum() {

        Quantity<WeightUnit> first =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateTargetUnitAddition(
                        first,
                        second,
                        WeightUnit.GRAM
                );

        assertEquals(2000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // Tests rejection of null measurements in equality demonstration.
    @Test
    void givenNullMeasurement_whenEqualityDemonstrated_shouldThrowException() {

        Quantity<LengthUnit> quantity =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateEquality(
                        quantity,
                        null
                )
        );
    }

    // Tests rejection of null measurement in conversion demonstration.
    @Test
    void givenNullMeasurement_whenConversionDemonstrated_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateConversion(
                        null,
                        LengthUnit.INCHES
                )
        );
    }

    // Tests rejection of null target unit in conversion demonstration.
    @Test
    void givenNullTargetUnit_whenConversionDemonstrated_shouldThrowException() {

        Quantity<LengthUnit> quantity =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateConversion(
                        quantity,
                        null
                )
        );
    }
}
