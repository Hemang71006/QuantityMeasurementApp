package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.service.IQuantityMeasurementService;

import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Handles quantity measurement requests and presents service results.
 */

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    // Constructor injection of the service interface.
    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        if (service == null) {
            throw new IllegalArgumentException(
                    "Service cannot be null"
            );
        }

        this.service = service;
    }

    public QuantityDTO compare(
            QuantityDTO first,
            QuantityDTO second) {

        return displayResult(service.compare(first, second));
    }

    public QuantityDTO convert(
            QuantityDTO quantity,
            String targetUnit) {

        return displayResult(service.convert(quantity, targetUnit));
    }

    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second) {

        return displayResult(service.add(first, second));
    }

    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return displayResult(
                service.add(first, second, targetUnit)
        );
    }

    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second) {

        return displayResult(service.subtract(first, second));
    }

    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return displayResult(
                service.subtract(first, second, targetUnit)
        );
    }

    public QuantityDTO divide(
            QuantityDTO first,
            QuantityDTO second) {

        return displayResult(service.divide(first, second));
    }

    public List<QuantityDTO> getMeasurementHistory() {
        return service.getMeasurementHistory();
    }

    // Handles presentation of successful and failed operation results.
    private QuantityDTO displayResult(QuantityDTO result) {

        if (result == null) {
            System.out.println("No result returned.");
            return null;
        }

        if (result.hasError()) {
            System.out.println(
                    "Operation failed: " + result.getMessage()
            );
            return result;
        }

        if ("COMPARE".equalsIgnoreCase(result.getOperation())) {
            System.out.println(
                    "Comparison Result: " + result.isResult()
            );
        } else {
            System.out.println(
                    "Operation Result: " + result
            );
        }

        return result;
    }
}