
package com.quantitymeasurement;

/*
 * Project Name : Quantity Measurement App
 * Module : UC2 - Feet and Inches Measurement Equality
 * Author : Hemang
 * Description : Compares Feet and Inches measurements using value-based equality.
 */

public class QuantityMeasurementApp {

    // Represents an immutable measurement value in feet.
    public static final class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
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
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static final class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
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
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean areFeetEqual(double value1, double value2) {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);

        return feet1.equals(feet2);
    }

    public static boolean areInchesEqual(double value1, double value2) {
        Inches inch1 = new Inches(value1);
        Inches inch2 = new Inches(value2);

        return inch1.equals(inch2);
    }

    public static boolean areFeetAndInchesEqual(double feetValue, double inchValue) {
        double convertedFeet = feetValue * 12.0;

        return Double.compare(convertedFeet, inchValue) == 0;
    }

    public static void main(String[] args) {

        System.out.println("Quantity Measurement App");
        System.out.println("-------------------------");

        System.out.println(
                "1.0 inch and 1.0 inch: " + areInchesEqual(1.0, 1.0)
        );

        System.out.println(
                "1.0 ft and 1.0 ft: " + areFeetEqual(1.0, 1.0)
        );

        System.out.println(
                "1.0 ft and 12.0 inches: " + areFeetAndInchesEqual(1.0, 12.0)
        );
    }
}
