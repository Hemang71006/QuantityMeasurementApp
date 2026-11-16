
package com.quantitymeasurement;

import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.QuantityLength;
import com.quantitymeasurement.model.WeightUnit;
import com.quantitymeasurement.model.QuantityWeight;

/*
 * Project Name : Quantity Measurement App
 * Module : UC9 - Weight Measurement Equality, Conversion, and Addition
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Demonstrates length and weight equality, conversion, and addition.
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
    public static boolean areFeetEqual(double value1, double value2) {

        return new QuantityLength(value1, LengthUnit.FEET)
                .equals(new QuantityLength(value2, LengthUnit.FEET));
    }

    // Compares two inches measurements.
    public static boolean areInchesEqual(double value1, double value2) {

        return new QuantityLength(value1, LengthUnit.INCHES)
                .equals(new QuantityLength(value2, LengthUnit.INCHES));
    }

    // Compares feet and inches measurements.
    public static boolean areFeetAndInchesEqual(
            double feetValue,
            double inchesValue
    ) {

        return new QuantityLength(feetValue, LengthUnit.FEET)
                .equals(new QuantityLength(inchesValue, LengthUnit.INCHES));
    }

    // Demonstrates conversion using a raw numeric value and two units.
    public static double demonstrateLengthConversion(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit
    ) {

        double result =
                QuantityLength.convert(value, sourceUnit, targetUnit);

        System.out.println(
                value + " " + sourceUnit + " = "
                        + result + " " + targetUnit
        );

        return result;
    }

    // Demonstrates conversion using an existing QuantityLength object.
    public static QuantityLength demonstrateLengthConversion(
            QuantityLength measurement,
            LengthUnit targetUnit
    ) {

        if (measurement == null) {
            throw new IllegalArgumentException(
                    "Measurement cannot be null"
            );
        }

        QuantityLength result = measurement.convertTo(targetUnit);

        System.out.println(measurement + " = " + result);

        return result;
    }

    // Demonstrates equality between two QuantityLength objects.
    public static boolean demonstrateLengthEquality(
            QuantityLength first,
            QuantityLength second
    ) {

        boolean result = first.equals(second);

        System.out.println(
                first + " and " + second + " are equal: " + result
        );

        return result;
    }

    // Creates two measurements and demonstrates their equality.
    public static boolean demonstrateLengthComparison(
            double firstValue,
            LengthUnit firstUnit,
            double secondValue,
            LengthUnit secondUnit
    ) {

        QuantityLength first =
                new QuantityLength(firstValue, firstUnit);

        QuantityLength second =
                new QuantityLength(secondValue, secondUnit);

        return demonstrateLengthEquality(first, second);
    }

    // Demonstrates addition of two length measurements in the first unit.
    public static QuantityLength demonstrateLengthAddition(
            QuantityLength first,
            QuantityLength second
    ) {

        QuantityLength result = QuantityLength.add(first, second);

        System.out.println(
                first + " + " + second + " = " + result
        );

        return result;
    }

    // Demonstrates addition with an explicitly specified target unit.
    public static QuantityLength demonstrateTargetUnitAddition(
            QuantityLength first,
            QuantityLength second,
            LengthUnit targetUnit
    ) {

        QuantityLength result =
                QuantityLength.add(first, second, targetUnit);

        System.out.println(
                first + " + " + second
                        + " = " + result
                        + " (Target Unit: " + targetUnit + ")"
        );

        return result;
    }

    // Demonstrates weight equality.
    public static boolean demonstrateWeightEquality(
            QuantityWeight first,
            QuantityWeight second
    ) {

        boolean result = first.equals(second);

        System.out.println(
                first + " and " + second + " are equal: " + result
        );

        return result;
    }

    // Demonstrates weight conversion.
    public static QuantityWeight demonstrateWeightConversion(
            QuantityWeight measurement,
            WeightUnit targetUnit
    ) {

        if (measurement == null) {
            throw new IllegalArgumentException(
                    "Measurement cannot be null"
            );
        }

        QuantityWeight result = measurement.convertTo(targetUnit);

        System.out.println(measurement + " = " + result);

        return result;
    }

    // Demonstrates weight addition with the first operand's unit.
    public static QuantityWeight demonstrateWeightAddition(
            QuantityWeight first,
            QuantityWeight second
    ) {

        QuantityWeight result = QuantityWeight.add(first, second);

        System.out.println(
                first + " + " + second + " = " + result
        );

        return result;
    }

    // Demonstrates weight addition with an explicit target unit.
    public static QuantityWeight demonstrateWeightTargetUnitAddition(
            QuantityWeight first,
            QuantityWeight second,
            WeightUnit targetUnit
    ) {

        QuantityWeight result =
                QuantityWeight.add(first, second, targetUnit);

        System.out.println(
                first + " + " + second
                        + " = " + result
                        + " (Target Unit: " + targetUnit + ")"
        );

        return result;
    }

    // Application entry point.
    public static void main(String[] args) {

        System.out.println("Quantity Measurement App - UC9");
        System.out.println("--------------------------------");

        System.out.println("\nUnit Conversion:");

        demonstrateLengthConversion(
                1.0, LengthUnit.FEET, LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3.0, LengthUnit.YARDS, LengthUnit.FEET
        );

        demonstrateLengthConversion(
                36.0, LengthUnit.INCHES, LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                0.0, LengthUnit.FEET, LengthUnit.INCHES
        );

        System.out.println("\nInstance Conversion:");

        QuantityLength lengthInYards =
                new QuantityLength(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                lengthInYards, LengthUnit.INCHES
        );

        System.out.println("\nLength Equality:");

        demonstrateLengthComparison(
                1.0, LengthUnit.YARDS,
                3.0, LengthUnit.FEET
        );

        demonstrateLengthComparison(
                1.0, LengthUnit.FEET,
                12.0, LengthUnit.INCHES
        );

        System.out.println("\nLength Addition:");

        demonstrateLengthAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET)
        );

        demonstrateLengthAddition(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );

        demonstrateLengthAddition(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET)
        );

        System.out.println("\nUC7: Addition with Target Unit Specification");
        System.out.println("--------------------------------------------");

        demonstrateTargetUnitAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARDS
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS),
                LengthUnit.FEET
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        demonstrateTargetUnitAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        // UC9: Weight Measurement Equality, Conversion, and Addition
        System.out.println("\n========== UC9: WEIGHT MEASUREMENT ==========");

        System.out.println("\nEquality Comparisons:");

        QuantityWeight oneKg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight thousandGrams =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight equivalentPounds =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        demonstrateWeightEquality(oneKg, thousandGrams);
        demonstrateWeightEquality(oneKg, equivalentPounds);

        demonstrateWeightEquality(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                new QuantityWeight(0.5, WeightUnit.KILOGRAM)
        );

        demonstrateWeightEquality(
                new QuantityWeight(1.0, WeightUnit.POUND),
                new QuantityWeight(453.592, WeightUnit.GRAM)
        );

        System.out.println("\nUnit Conversions:");

        demonstrateWeightConversion(oneKg, WeightUnit.GRAM);

        demonstrateWeightConversion(
                new QuantityWeight(2.0, WeightUnit.POUND),
                WeightUnit.KILOGRAM
        );

        demonstrateWeightConversion(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                WeightUnit.POUND
        );

        demonstrateWeightConversion(
                new QuantityWeight(0.0, WeightUnit.KILOGRAM),
                WeightUnit.GRAM
        );

        System.out.println("\nAddition with Implicit Target Unit:");

        demonstrateWeightAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );

        demonstrateWeightAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );

        demonstrateWeightAddition(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                new QuantityWeight(0.5, WeightUnit.KILOGRAM)
        );

        System.out.println("\nAddition with Explicit Target Unit:");

        demonstrateWeightTargetUnitAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );

        demonstrateWeightTargetUnitAddition(
                new QuantityWeight(1.0, WeightUnit.POUND),
                new QuantityWeight(453.592, WeightUnit.GRAM),
                WeightUnit.POUND
        );

        demonstrateWeightTargetUnitAddition(
                new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                new QuantityWeight(4.0, WeightUnit.POUND),
                WeightUnit.KILOGRAM
        );

        System.out.println("\nCategory Incompatibility:");

        System.out.println(
                "1 KG = 1 FOOT: "
                        + oneKg.equals(
                        new QuantityLength(1.0, LengthUnit.FEET)
                )
        );
    }
}
