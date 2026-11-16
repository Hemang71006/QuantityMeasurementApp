
package com.quantitymeasurement;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.VolumeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Volume Application Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests generic application methods with the volume category.
 */

class QuantityMeasurementAppVolumeTest {

    // Tests generic equality for volume measurements.
    @Test
    void givenEquivalentVolumes_whenEqualityDemonstrated_shouldReturnTrue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitres =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(
                QuantityMeasurementApp.demonstrateEquality(
                        litre,
                        millilitres
                )
        );
    }

    // Tests generic conversion for volume measurements.
    @Test
    void givenLitre_whenConversionDemonstrated_shouldReturnMillilitres() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                QuantityMeasurementApp.demonstrateConversion(
                        litre,
                        VolumeUnit.MILLILITRE
                );

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    // Tests generic addition for volume measurements.
    @Test
    void givenVolumeQuantities_whenAdditionDemonstrated_shouldReturnSum() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitres =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                QuantityMeasurementApp.demonstrateAddition(
                        litre,
                        millilitres
                );

        assertEquals(1.5, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    // Tests generic addition with a target unit.
    @Test
    void givenVolumeQuantities_whenTargetAdditionDemonstrated_shouldReturnMillilitres() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitres =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                QuantityMeasurementApp.demonstrateTargetUnitAddition(
                        litre,
                        millilitres,
                        VolumeUnit.MILLILITRE
                );

        assertEquals(1500.0, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    // Tests wildcard display with a volume quantity.
    @Test
    void givenVolumeQuantity_whenDisplayed_shouldNotThrowException() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertDoesNotThrow(
                () -> QuantityMeasurementApp.displayMeasurement(litre)
        );
    }

    // Tests rejection of null in wildcard display.
    @Test
    void givenNullQuantity_whenDisplayed_shouldThrowException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.displayMeasurement(null)
        );
    }
}
