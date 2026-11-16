
package com.quantitymeasurement;

import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.QuantityLength;
import com.quantitymeasurement.model.VolumeUnit;
import com.quantitymeasurement.model.WeightUnit;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Generic Quantity Application
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Demonstrates generic operations across measurement categories.
 */

public class QuantityMeasurementApp {

    // Maintains compatibility with the UC1 Feet implementation.
    public static final class Feet {

        private final QuantityLength quantity;

        public Feet(double value) {
            quantity = new QuantityLength(value, LengthUnit.FEET);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;
            return quantity.equals(other.quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    // Maintains compatibility with the UC2 Inches implementation.
    public static final class Inches {

        private final QuantityLength quantity;

        public Inches(double value) {
            quantity = new QuantityLength(value, LengthUnit.INCHES);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Inches other = (Inches) obj;
            return quantity.equals(other.quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    // Compares two feet measurements.
    public static boolean areFeetEqual(double first, double second) {
        return new QuantityLength(first, LengthUnit.FEET)
                .equals(new QuantityLength(second, LengthUnit.FEET));
    }

    // Compares two inches measurements.
    public static boolean areInchesEqual(double first, double second) {
        return new QuantityLength(first, LengthUnit.INCHES)
                .equals(new QuantityLength(second, LengthUnit.INCHES));
    }

    // Compares feet and inches measurements.
    public static boolean areFeetAndInchesEqual(
            double feetValue,
            double inchesValue) {

        return new QuantityLength(feetValue, LengthUnit.FEET)
                .equals(new QuantityLength(inchesValue, LengthUnit.INCHES));
    }

    // Displays a quantity without requiring its measurement category.
    public static void displayMeasurement(Quantity<?> measurement) {

        if (measurement == null) {
            throw new IllegalArgumentException(
                    "Measurement cannot be null"
            );
        }

        System.out.println("Measurement: " + measurement);
    }

    // Demonstrates equality for any two generic quantities.
    public static boolean demonstrateEquality(
            Quantity<?> first,
            Quantity<?> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        boolean result = first.equals(second);

        System.out.println(
                first + " and " + second + " are equal: " + result
        );

        return result;
    }

    // Demonstrates conversion for any supported measurement category.
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> measurement,
            U targetUnit) {

        if (measurement == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Measurement and target unit cannot be null"
            );
        }

        Quantity<U> result = measurement.convertTo(targetUnit);

        System.out.println(measurement + " = " + result);

        return result;
    }

    // Demonstrates addition using the first measurement's unit.
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> first,
            Quantity<U> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        Quantity<U> result = first.add(second);

        System.out.println(
                first + " + " + second + " = " + result
        );

        return result;
    }

    // Demonstrates addition using an explicitly selected target unit.
    public static <U extends IMeasurable>
    Quantity<U> demonstrateTargetUnitAddition(
            Quantity<U> first,
            Quantity<U> second,
            U targetUnit) {

        if (first == null || second == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Measurements and target unit cannot be null"
            );
        }

        Quantity<U> result = first.add(second, targetUnit);

        System.out.println(
                first + " + " + second
                        + " = " + result
                        + " (Target Unit: " + targetUnit.getUnitName() + ")"
        );

        return result;
    }

    // Application entry point.
    public static void main(String[] args) {

        System.out.println("Quantity Measurement App - UC10");
        System.out.println("--------------------------------");

        System.out.println("\nLength:");

        Quantity<LengthUnit> oneFoot =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> twelveInches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(oneFoot, twelveInches);
        demonstrateConversion(oneFoot, LengthUnit.INCHES);
        demonstrateAddition(oneFoot, twelveInches);

        demonstrateTargetUnitAddition(
                oneFoot,
                twelveInches,
                LengthUnit.INCHES
        );

        System.out.println("\nWeight:");

        Quantity<WeightUnit> oneKilogram =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> thousandGrams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(oneKilogram, thousandGrams);
        demonstrateConversion(oneKilogram, WeightUnit.GRAM);
        demonstrateAddition(oneKilogram, thousandGrams);

        demonstrateTargetUnitAddition(
                oneKilogram,
                thousandGrams,
                WeightUnit.GRAM
        );

        System.out.println("\nVolume:");

        Quantity<VolumeUnit> oneLitre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> fiveHundredMillilitres =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateEquality(
                oneLitre,
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
        );

        demonstrateConversion(
                oneLitre,
                VolumeUnit.MILLILITRE
        );

        demonstrateAddition(
                oneLitre,
                fiveHundredMillilitres
        );

        System.out.println("\nWildcard Demonstration:");

        displayMeasurement(oneFoot);
        displayMeasurement(oneKilogram);
        displayMeasurement(oneLitre);

        System.out.println("\nDifferent Category Comparison:");

        System.out.println(
                "1 KILOGRAM = 1 FOOT: "
                        + oneKilogram.equals(oneFoot)
        );
    }
}
