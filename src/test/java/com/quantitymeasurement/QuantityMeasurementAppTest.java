
package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Project Name : Quantity Measurement App
 * Module : UC2 - Feet and Inches Measurement Equality
 * Author : Hemang
 * Description : Tests equality for Feet, Inches, and cross-unit measurements.
 */

class QuantityMeasurementAppTest {

    // Tests measurement equality and cross-unit conversion.

    @Test
    void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);

        assertNotEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(feet, null);
    }

    @Test
    void testFeetEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(feet, "1.0");
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet, feet);
    }

    @Test
    void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches inch1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inch2 = new QuantityMeasurementApp.Inches(1.0);

        assertEquals(inch1, inch2);
    }

    @Test
    void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches inch1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inch2 = new QuantityMeasurementApp.Inches(2.0);

        assertNotEquals(inch1, inch2);
    }

    @Test
    void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);

        assertNotEquals(inch, null);
    }

    @Test
    void testInchesEquality_NonNumericInput() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);

        assertNotEquals(inch, "1.0");
    }

    @Test
    void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);

        assertEquals(inch, inch);
    }

    @Test
    void testFeetEqualityMethod() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    @Test
    void testInchesEqualityMethod() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    void testFeetAndInchesEquality() {
        assertTrue(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 12.0));
    }

    @Test
    void testFeetAndInchesDifferentValue() {
        assertFalse(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 10.0));
    }

    @Test
    void testEqualObjectsHaveSameHashCode() {
        QuantityMeasurementApp.Inches inch1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inch2 = new QuantityMeasurementApp.Inches(1.0);

        assertEquals(inch1.hashCode(), inch2.hashCode());
    }
}
