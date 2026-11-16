
package com.quantitymeasurement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureMeasurementTest {

    private static final double DELTA = 1e-9;

    @Test
    void givenCelsiusAndFahrenheit_whenCompared_shouldBeEqual() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(celsius, fahrenheit);
        assertEquals(fahrenheit, celsius);
    }

    @Test
    void givenCelsiusAndKelvin_whenCompared_shouldBeEqual() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> kelvin =
                new Quantity<>(273.15, TemperatureUnit.KELVIN);

        assertEquals(celsius, kelvin);
    }

    @Test
    void givenSameTemperature_whenCompared_shouldBeEqual() {
        Quantity<TemperatureUnit> first =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void givenDifferentTemperatures_whenCompared_shouldNotBeEqual() {
        Quantity<TemperatureUnit> first =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second =
                new Quantity<>(30.0, TemperatureUnit.CELSIUS);

        assertNotEquals(first, second);
    }

    @Test
    void givenNull_whenCompared_shouldNotBeEqual() {
        Quantity<TemperatureUnit> temperature =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertNotEquals(null, temperature);
    }

    @Test
    void givenOneHundredCelsius_whenConvertedToFahrenheit_shouldReturn212() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), DELTA);
        assertEquals(TemperatureUnit.FAHRENHEIT, result.getUnit());
    }

    @Test
    void givenThirtyTwoFahrenheit_whenConvertedToCelsius_shouldReturnZero() {
        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                fahrenheit.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    void given273Point15Kelvin_whenConvertedToCelsius_shouldReturnZero() {
        Quantity<TemperatureUnit> kelvin =
                new Quantity<>(273.15, TemperatureUnit.KELVIN);

        Quantity<TemperatureUnit> result =
                kelvin.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    void givenZeroCelsius_whenConvertedToKelvin_shouldReturn273Point15() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.KELVIN);

        assertEquals(273.15, result.getValue(), DELTA);
    }

    @Test
    void givenMinusFortyCelsius_whenConvertedToFahrenheit_shouldReturnMinusForty() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-40.0, result.getValue(), DELTA);
    }

    @Test
    void givenAbsoluteZeroCelsius_whenConvertedToKelvin_shouldReturnZero() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-273.15, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.KELVIN);

        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    void givenTemperature_whenConvertedToSameUnit_shouldPreserveValue() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(45.5, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(45.5, result.getValue(), DELTA);
    }

    @Test
    void givenTemperature_whenConvertedBack_shouldPreserveOriginalValue() {
        Quantity<TemperatureUnit> original =
                new Quantity<>(75.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(original.getValue(), converted.getValue(), DELTA);
    }

    @Test
    void givenTemperature_whenAdditionAttempted_shouldThrowException() {
        Quantity<TemperatureUnit> first =
                new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second =
                new Quantity<>(30.0, TemperatureUnit.CELSIUS);

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> first.add(second)
                );

        assertTrue(exception.getMessage().toLowerCase().contains("add"));
    }

    @Test
    void givenTemperature_whenSubtractionAttempted_shouldThrowException() {
        Quantity<TemperatureUnit> first =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second =
                new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> first.subtract(second)
                );

        assertTrue(exception.getMessage().toLowerCase().contains("subtract"));
    }

    @Test
    void givenTemperature_whenDivisionAttempted_shouldThrowException() {
        Quantity<TemperatureUnit> first =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> second =
                new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> first.divide(second)
                );

        assertTrue(exception.getMessage().toLowerCase().contains("divide"));
    }

    @Test
    void givenTemperatureUnit_whenCapabilitiesChecked_shouldReturnFalse() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
        assertFalse(TemperatureUnit.CELSIUS.supportsAddition());
        assertFalse(TemperatureUnit.CELSIUS.supportsSubtraction());
        assertFalse(TemperatureUnit.CELSIUS.supportsDivision());
    }

    @Test
    void givenLengthWeightAndVolume_whenCapabilitiesChecked_shouldReturnTrue() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
        assertTrue(LengthUnit.FEET.supportsAddition());
        assertTrue(LengthUnit.FEET.supportsSubtraction());
        assertTrue(LengthUnit.FEET.supportsDivision());

        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
        assertTrue(WeightUnit.KILOGRAM.supportsDivision());

        assertTrue(VolumeUnit.LITRE.supportsArithmetic());
        assertTrue(VolumeUnit.LITRE.supportsAddition());
    }

    @Test
    void givenTemperatureUnit_whenOperationValidated_shouldThrowException() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS
                        .validateOperationSupport("addition")
        );
    }

    @Test
    void givenNullTargetUnit_whenConverted_shouldThrowException() {
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertThrows(
                IllegalArgumentException.class,
                () -> celsius.convertTo(null)
        );
    }

    @Test
    void givenNullUnit_whenQuantityCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(25.0, null)
        );
    }

    @Test
    void givenInfiniteValue_whenQuantityCreated_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.POSITIVE_INFINITY,
                        TemperatureUnit.CELSIUS
                )
        );
    }

    @Test
    void givenDifferentMeasurementCategories_whenCompared_shouldNotBeEqual() {
        Quantity<TemperatureUnit> temperature =
                new Quantity<>(1.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertNotEquals(temperature, length);
        assertNotEquals(temperature, weight);
        assertNotEquals(temperature, volume);
    }

    @Test
    void givenDifferentTemperatureUnits_whenConverted_shouldPreserveEquality() {
        Quantity<TemperatureUnit> original =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                original.convertTo(TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> kelvin =
                original.convertTo(TemperatureUnit.KELVIN);

        assertEquals(original, fahrenheit);
        assertEquals(original, kelvin);
    }
}
