package com.quantitymeasurement.exception;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Custom unchecked exception for quantity measurement operations.
 */

public class QuantityMeasurementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QuantityMeasurementException(String message) {
        super(message);
    }

    public QuantityMeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}