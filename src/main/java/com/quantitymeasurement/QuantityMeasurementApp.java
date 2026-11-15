package com.quantitymeasurement;

import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.QuantityLength;

/*
 * Project Name : Quantity Measurement App
 * Module : UC3 - Generic Quantity Length
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Runs the quantity measurement application and maintains compatibility with previous use cases.
 */

public class QuantityMeasurementApp {

    // Maintains backward compatibility with the previous Feet implementation.
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

    // Maintains backward compatibility with the previous Inches implementation.
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

        QuantityLength first =
                new QuantityLength(value1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(value2, LengthUnit.FEET);

        return first.equals(second);
    }

    // Compares two inches measurements.
    public static boolean areInchesEqual(double value1, double value2) {

        QuantityLength first =
                new QuantityLength(value1, LengthUnit.INCHES);

        QuantityLength second =
                new QuantityLength(value2, LengthUnit.INCHES);

        return first.equals(second);
    }

    // Compares feet and inches measurements.
    public static boolean areFeetAndInchesEqual(
            double feetValue,
            double inchesValue
    ) {

        QuantityLength feet =
                new QuantityLength(feetValue, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(inchesValue, LengthUnit.INCHES);

        return feet.equals(inches);
    }

    // Application entry point.
    // Demonstrates equality across all supported length units.
    public static void main(String[] args) {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength threeFeet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength thirtySixInches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength twoYards =
                new QuantityLength(2.0, LengthUnit.YARDS);

        QuantityLength twoCentimeters =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        QuantityLength oneCentimeter =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength equivalentInches =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println("Quantity Measurement App - UC4");
        System.out.println("--------------------------------");

        System.out.println(
                "1 yard and 3 feet: " + yard.equals(threeFeet)
        );

        System.out.println(
                "1 yard and 36 inches: " + yard.equals(thirtySixInches)
        );

        System.out.println(
                "2 yards and 2 yards: " + twoYards.equals(
                        new QuantityLength(2.0, LengthUnit.YARDS)
                )
        );

        System.out.println(
                "2 cm and 2 cm: " + twoCentimeters.equals(
                        new QuantityLength(2.0, LengthUnit.CENTIMETERS)
                )
        );

        System.out.println(
                "1 cm and 0.393701 inches: " +
                        oneCentimeter.equals(equivalentInches)
        );
    }
}