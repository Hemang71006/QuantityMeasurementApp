
package com.quantitymeasurement;

/*
 * Project Name : Quantity Measurement App
 * Module : UC1 - Feet Measurement Equality
 * Author : Hemang
 * Description : Compares two Feet measurement objects for value-based equality.
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

    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        System.out.println("Feet 1: 1.0 ft");
        System.out.println("Feet 2: 1.0 ft");
        System.out.println("Equal: " + feet1.equals(feet2));
    }
}
