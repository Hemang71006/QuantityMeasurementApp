
package com.quantitymeasurement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Handles REST API exceptions and returns consistent error responses.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles invalid request body fields rejected by Bean Validation.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, Object> errors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                errors);
    }

    // Handles errors raised by the quantity measurement application.
    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<Map<String, Object>> handleQuantityException(
            QuantityMeasurementException exception) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                null);
    }

    // Handles invalid arguments and arithmetic errors.
    @ExceptionHandler({IllegalArgumentException.class, ArithmeticException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(
            RuntimeException exception) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                null);
    }

    // Handles unexpected server-side errors.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(
            Exception exception) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected server error occurred",
                null);
    }

    // Builds the standard error response body.
    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status,
            String message,
            Object details) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        if (details != null) {
            body.put("details", details);
        }

        return ResponseEntity.status(status).body(body);
    }
}
