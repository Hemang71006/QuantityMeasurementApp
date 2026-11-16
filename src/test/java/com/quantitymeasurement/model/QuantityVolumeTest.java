
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Volume Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Verifies that a new measurement category reuses generic Quantity.
 */

class QuantityVolumeTest {

    // Tests that volume units implement the common measurable interface.
    @Test
    void givenVolumeUnits_whenChecked_shouldImplementIMeasurable() {

        assertInstanceOf(
                com.quantitymeasurement.interfaces.IMeasurable.class,
                VolumeUnit.LITRE
        );

        assertInstanceOf(
                com.quantitymeasurement.interfaces.IMeasurable.class,
                VolumeUnit.MILLILITRE
        );
    }

    // Tests conversion from litres to millilitres.
    @Test
    void givenLitres_whenConvertedToMillilitres_shouldReturnCorrectValue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    // Tests conversion from millilitres to litres.
    @Test
    void givenMillilitres_whenConvertedToLitres_shouldReturnCorrectValue() {

        Quantity<VolumeUnit> millilitres =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                millilitres.convertTo(VolumeUnit.LITRE);

        assertEquals(0.5, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    // Tests equality between equivalent volume quantities.
    @Test
    void givenEquivalentVolumeQuantities_whenCompared_shouldReturnTrue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitres =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitres);
    }

    // Tests addition of volume quantities.
    @Test
    void givenVolumeQuantities_whenAdded_shouldReturnSumInFirstUnit() {

        Quantity<VolumeUnit> first =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(1.5, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    // Tests addition with an explicitly selected target unit.
    @Test
    void givenVolumeQuantities_whenAddedWithTargetUnit_shouldReturnTargetUnitSum() {

        Quantity<VolumeUnit> first =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                first.add(second, VolumeUnit.MILLILITRE);

        assertEquals(1500.0, result.getValue(), 0.000001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }
}
