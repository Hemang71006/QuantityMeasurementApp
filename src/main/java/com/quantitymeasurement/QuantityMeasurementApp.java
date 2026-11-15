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
    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength firstInch =
                new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength secondInch =
                new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println("Quantity Measurement App");
        System.out.println("-------------------------");

        System.out.println(
                "1.0 ft and 12.0 inches: " + feet.equals(inches)
        );

        System.out.println(
                "1.0 inch and 1.0 inch: " +
                        firstInch.equals(secondInch)
        );

        System.out.println(
                "1.0 ft and 1.0 ft: " +
                        areFeetEqual(1.0, 1.0)
        );
    }
}