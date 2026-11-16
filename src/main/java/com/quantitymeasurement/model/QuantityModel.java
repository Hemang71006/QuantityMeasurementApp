package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Represents a quantity internally using its value and measurement unit.
 */

public class QuantityModel<U extends IMeasurable> {

    private double value;
    private U unit;

    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public U getUnit() {
        return unit;
    }

    public void setUnit(U unit) {
        this.unit = unit;
    }

    // Converts this model into the generic Quantity object.
    public Quantity<U> toQuantity() {
        return new Quantity<>(value, unit);
    }

    @Override
    public String toString() {
        return "QuantityModel{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}