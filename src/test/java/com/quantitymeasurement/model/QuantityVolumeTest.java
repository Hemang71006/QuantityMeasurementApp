package com.quantitymeasurement.model;

/*
 * Project Name : Quantity Measurement App
 * Module : UC11 - Volume Measurement
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Tests volume equality, conversion, addition, and category isolation.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityVolumeTest {

    private static final double EPSILON = 1e-6;

    @Test
    void givenSameLitreValues_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(first, second);
    }

    @Test
    void givenDifferentLitreValues_whenCompared_shouldReturnFalse() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(2.0, VolumeUnit.LITRE);

        assertNotEquals(first, second);
    }

    @Test
    void givenEquivalentLitreAndMillilitre_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitre);
        assertEquals(millilitre, litre);
    }

    @Test
    void givenEquivalentLitreAndGallon_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertEquals(litre, gallon);
        assertEquals(gallon, litre);
    }

    @Test
    void givenSameGallonValues_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> second = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertEquals(first, second);
    }

    @Test
    void givenVolumeAndLength_whenCompared_shouldReturnFalse() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertNotEquals(volume, length);
    }

    @Test
    void givenVolumeAndWeight_whenCompared_shouldReturnFalse() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(volume, weight);
    }

    @Test
    void givenNull_whenCompared_shouldReturnFalse() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertNotEquals(null, volume);
    }

    @Test
    void givenSameReference_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(volume, volume);
    }

    @Test
    void givenNullUnit_whenCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void givenZeroVolumeInDifferentUnits_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> litre = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitre);
    }

    @Test
    void givenNegativeEquivalentVolumes_whenCompared_shouldReturnTrue() {
        Quantity<VolumeUnit> litre = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitre);
    }

    @Test
    void givenLitre_whenConvertedToMillilitre_shouldReturnCorrectValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void givenMillilitre_whenConvertedToLitre_shouldReturnCorrectValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenGallon_whenConvertedToLitre_shouldReturnCorrectValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenLitre_whenConvertedToGallon_shouldReturnCorrectValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    void givenMillilitre_whenConvertedToGallon_shouldReturnCorrectValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.GALLON);

        assertEquals(1000.0 / 3785.41, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    void givenVolume_whenConvertedToSameUnit_shouldPreserveValue() {
        Quantity<VolumeUnit> volume = new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenZeroVolume_whenConverted_shouldRemainZero() {
        Quantity<VolumeUnit> volume = new Quantity<>(0.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void givenVolume_whenConvertedToAnotherUnitAndBack_shouldPreserveValue() {
        Quantity<VolumeUnit> original = new Quantity<>(1.5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = original
                .convertTo(VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    void givenTwoLitreQuantities_whenAdded_shouldReturnSumInLitre() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenLitreAndMillilitre_whenAdded_shouldReturnSumInFirstUnit() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenMillilitreAndLitre_whenAdded_shouldReturnSumInMillilitre() {
        Quantity<VolumeUnit> first = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void givenGallonAndLitre_whenAdded_shouldReturnSumInGallon() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> second = new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    void givenLitreAndMillilitre_whenAddedWithGallonTarget_shouldReturnGallonSum() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second, VolumeUnit.GALLON);

        assertEquals(2.0 / 3.78541, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    void givenVolumes_whenAddedWithMillilitreTarget_shouldReturnMillilitreSum() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void givenVolumeAndZero_whenAdded_shouldPreserveOriginalValue() {
        Quantity<VolumeUnit> first = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = first.add(second);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenVolumes_whenAdded_shouldNotModifyOriginalQuantities() {
        Quantity<VolumeUnit> first = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        first.add(second);

        assertEquals(1.0, first.getValue(), EPSILON);
        assertEquals(1000.0, second.getValue(), EPSILON);
    }

    @Test
    void givenLitreUnit_whenBaseConversionRequested_shouldReturnSameValue() {
        assertEquals(5.0, VolumeUnit.LITRE.convertToBaseUnit(5.0), EPSILON);
        assertEquals(2.0, VolumeUnit.LITRE.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void givenMillilitreUnit_whenBaseConversionRequested_shouldConvertCorrectly() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void givenGallonUnit_whenBaseConversionRequested_shouldConvertCorrectly() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
        assertEquals(1.0, VolumeUnit.GALLON.convertFromBaseUnit(3.78541), EPSILON);
    }

    @Test
    void givenVolumeUnits_whenConversionFactorsRequested_shouldReturnCorrectFactors() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    void givenNonFiniteVolume_whenCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, VolumeUnit.LITRE)
        );
    }

    @Test
    void givenNonFiniteValue_whenConvertedToBaseUnit_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> VolumeUnit.GALLON.convertToBaseUnit(Double.NaN)
        );
    }
}