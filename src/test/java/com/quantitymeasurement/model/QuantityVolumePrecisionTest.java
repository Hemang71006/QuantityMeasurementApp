package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC11 - Volume Precision Tests
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Verifies that generic quantity conversions and additions preserve floating-point precision.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityVolumePrecisionTest {

    private static final double EPSILON = 1e-9;

    @Test
    void givenOneGallon_whenConvertedToLitre_shouldPreservePrecision() {
        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                gallon.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenOneLitre_whenConvertedToGallon_shouldPreservePrecision() {
        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.GALLON);

        assertEquals(1.0 / 3.78541, result.getValue(), EPSILON);
        assertNotEquals(0.26, result.getValue());
    }

    @Test
    void givenOneThousandMillilitres_whenConvertedToGallon_shouldPreservePrecision() {
        Quantity<VolumeUnit> millilitres =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                millilitres.convertTo(VolumeUnit.GALLON);

        assertEquals(1000.0 / 3785.41, result.getValue(), EPSILON);
        assertNotEquals(0.26, result.getValue());
    }

    @Test
    void givenTwoGallons_whenConvertedToLitre_shouldPreservePrecision() {
        Quantity<VolumeUnit> gallons =
                new Quantity<>(2.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                gallons.convertTo(VolumeUnit.LITRE);

        assertEquals(7.57082, result.getValue(), EPSILON);
    }

    @Test
    void givenVolumeAddition_whenTargetIsGallon_shouldPreservePrecision() {
        Quantity<VolumeUnit> first =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                first.add(second, VolumeUnit.GALLON);

        assertEquals(2.0 / 3.78541, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    void givenVolumeAddition_whenTargetIsMillilitre_shouldPreservePrecision() {
        Quantity<VolumeUnit> first =
                new Quantity<>(1.234, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(0.001, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                first.add(second, VolumeUnit.MILLILITRE);

        assertEquals(1235.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }
}