
package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC1 - Feet Measurement Equality
 * Author : Hemang
 * Description : Tests equality comparison between two Feet measurement objects.
 */

class QuantityMeasurementAppTest {

    // Tests the equality behavior of Feet measurement objects.

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet1, feet2, "Same feet values should be equal");
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);

        assertNotEquals(feet1, feet2, "Different feet values should not be equal");
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(null, feet, "Feet should not be equal to null");
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        Object nonNumericObject = "1.0";

        assertNotEquals(feet, nonNumericObject, "Feet should not equal a non-Feet object");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet, feet, "An object should be equal to itself");
    }

    @Test
    void testEquality_EqualObjectsHaveSameHashCode() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet1.hashCode(), feet2.hashCode());
    }
}
