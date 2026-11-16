
package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.interfaces.SupportsArithmetic;

import java.util.function.Function;

/*
 * Project Name : Quantity Measurement App
 * Module : UC14 - Temperature Measurement
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines temperature units and their non-linear conversions using Celsius as the base unit.
 */

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            value -> value,
            value -> value
    ),

    FAHRENHEIT(
            value -> (value - 32.0) * 5.0 / 9.0,
            value -> (value * 9.0 / 5.0) + 32.0
    ),

    KELVIN(
            value -> value - 273.15,
            value -> value + 273.15
    );

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(
            Function<Double, Double> toCelsius,
            Function<Double, Double> fromCelsius) {

        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    // Temperature conversion is offset-based, so no single multiplicative factor applies.
    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    // Converts the temperature into Celsius.
    @Override
    public double convertToBaseUnit(double value) {
        validateFinite(value);

        double result = toCelsius.apply(value);

        validateFiniteResult(result);

        return result;
    }

    // Converts Celsius into the requested temperature unit.
    @Override
    public double convertFromBaseUnit(double baseValue) {
        validateFinite(baseValue);

        double result = fromCelsius.apply(baseValue);

        validateFiniteResult(result);

        return result;
    }

    // Returns the name of the temperature unit.
    @Override
    public String getUnitName() {
        return name();
    }

    // Indicates that temperature does not support arithmetic operations.
    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public boolean supportsAddition() {
        return false;
    }

    @Override
    public boolean supportsSubtraction() {
        return false;
    }

    @Override
    public boolean supportsDivision() {
        return false;
    }

    // Rejects arithmetic operations that are not meaningful for absolute temperatures.
    @Override
    public void validateOperationSupport(String operation) {
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException(
                    "Operation name cannot be null or blank"
            );
        }

        throw new UnsupportedOperationException(
                "Temperature does not support " + operation.toLowerCase()
                        + " operation on absolute temperature values"
        );
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Temperature value must be finite"
            );
        }
    }

    private static void validateFiniteResult(double value) {
        if (!Double.isFinite(value)) {
            throw new ArithmeticException(
                    "Temperature conversion overflow"
            );
        }
    }
}
