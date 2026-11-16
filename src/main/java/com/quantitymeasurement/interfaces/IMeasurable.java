
package com.quantitymeasurement.interfaces;

/*
 * Project Name : Quantity Measurement App
 * Module : UC10 - Measurable Unit Interface
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines common conversion operations for all measurement units.
 */

public interface IMeasurable {

    // Returns the conversion factor relative to the category's base unit.
    double getConversionFactor();

    // Converts a value from this unit into the category's base unit.
    double convertToBaseUnit(double value);

    // Converts a value from the category's base unit into this unit.
    double convertFromBaseUnit(double baseValue);

    // Returns the name of the measurement unit.
    String getUnitName();
}
