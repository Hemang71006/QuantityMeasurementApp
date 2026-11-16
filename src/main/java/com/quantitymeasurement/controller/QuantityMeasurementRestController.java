
package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityConversionRequestDTO;
import com.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.quantitymeasurement.dto.QuantityOperationRequestDTO;
import com.quantitymeasurement.dto.QuantityOperationResponseDTO;
import com.quantitymeasurement.service.QuantityMeasurementSpringService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Exposes quantity measurement operations and history through REST endpoints.
 */

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementRestController {

    private final QuantityMeasurementSpringService service;

    public QuantityMeasurementRestController(
            QuantityMeasurementSpringService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityOperationResponseDTO> compare(
            @Valid @RequestBody QuantityOperationRequestDTO request) {
        return ResponseEntity.ok(
                service.compare(request.getFirst(), request.getSecond()));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityOperationResponseDTO> convert(
            @Valid @RequestBody QuantityConversionRequestDTO request) {
        return ResponseEntity.ok(
                service.convert(
                        request.getQuantity(),
                        request.getTargetUnit()));
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityOperationResponseDTO> add(
            @Valid @RequestBody QuantityOperationRequestDTO request) {
        return ResponseEntity.ok(
                service.add(request.getFirst(), request.getSecond()));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityOperationResponseDTO> subtract(
            @Valid @RequestBody QuantityOperationRequestDTO request) {
        return ResponseEntity.ok(
                service.subtract(request.getFirst(), request.getSecond()));
    }

    @PostMapping("/multiply")
    public ResponseEntity<QuantityOperationResponseDTO> multiply(
            @Valid @RequestBody QuantityOperationRequestDTO request) {
        return ResponseEntity.ok(
                service.multiply(request.getFirst(), request.getSecond()));
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityOperationResponseDTO> divide(
            @Valid @RequestBody QuantityOperationRequestDTO request) {
        return ResponseEntity.ok(
                service.divide(request.getFirst(), request.getSecond()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistory() {
        return ResponseEntity.ok(service.getHistory());
    }

    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(
            @PathVariable String operation) {
        return ResponseEntity.ok(
                service.getHistoryByOperation(operation));
    }

    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>>
    getHistoryByMeasurementType(@PathVariable String measurementType) {
        return ResponseEntity.ok(
                service.getHistoryByMeasurementType(measurementType));
    }

    @GetMapping("/history/errors")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        return ResponseEntity.ok(service.getErrorHistory());
    }

    @GetMapping("/history/count")
    public ResponseEntity<Long> countHistory() {
        return ResponseEntity.ok(service.countHistory());
    }
}
