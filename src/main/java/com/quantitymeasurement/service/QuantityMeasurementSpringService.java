
package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.dto.QuantityInputDTO;
import com.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.quantitymeasurement.dto.QuantityOperationResponseDTO;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.model.QuantityMeasurementEntity;
import com.quantitymeasurement.repository.QuantityMeasurementJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/*
 * Project Name : Quantity Measurement App
 * Module : UC17 - Spring Boot Integration
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Provides Spring-managed quantity operations and JPA history services.
 */

@Service
public class QuantityMeasurementSpringService {

    private final IQuantityMeasurementService measurementService;
    private final QuantityMeasurementJpaRepository jpaRepository;

    public QuantityMeasurementSpringService(
            IQuantityMeasurementService measurementService,
            QuantityMeasurementJpaRepository jpaRepository) {
        this.measurementService = measurementService;
        this.jpaRepository = jpaRepository;
    }

    // Converts API input into the existing service DTO.
    private QuantityDTO toQuantityDTO(QuantityInputDTO input) {
        if (input == null) {
            throw new QuantityMeasurementException("Quantity cannot be null");
        }

        return new QuantityDTO(
                input.getValue(),
                input.getMeasurementType(),
                input.getUnit()
        );
    }

    // Converts the existing service result into the REST response DTO.
    private QuantityOperationResponseDTO toResponse(QuantityDTO result) {
        return QuantityOperationResponseDTO.fromQuantityDTO(result);
    }

    public QuantityOperationResponseDTO compare(
            QuantityInputDTO first,
            QuantityInputDTO second) {
        return toResponse(measurementService.compare(
                toQuantityDTO(first), toQuantityDTO(second)));
    }

    public QuantityOperationResponseDTO convert(
            QuantityInputDTO quantity,
            String targetUnit) {
        return toResponse(measurementService.convert(
                toQuantityDTO(quantity), targetUnit));
    }

    public QuantityOperationResponseDTO add(
            QuantityInputDTO first,
            QuantityInputDTO second) {
        return toResponse(measurementService.add(
                toQuantityDTO(first), toQuantityDTO(second)));
    }

    public QuantityOperationResponseDTO subtract(
            QuantityInputDTO first,
            QuantityInputDTO second) {
        return toResponse(measurementService.subtract(
                toQuantityDTO(first), toQuantityDTO(second)));
    }

    public QuantityOperationResponseDTO divide(
            QuantityInputDTO first,
            QuantityInputDTO second) {
        return toResponse(measurementService.divide(
                toQuantityDTO(first), toQuantityDTO(second)));
    }

    // Multiplies two finite values and stores the scalar result in history.
    public QuantityOperationResponseDTO multiply(
            QuantityInputDTO first,
            QuantityInputDTO second) {

        validateMultiplicationInputs(first, second);

        double result = first.getValue() * second.getValue();

        if (!Double.isFinite(result)) {
            throw new QuantityMeasurementException(
                    "Multiplication result is not finite");
        }

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        first.getValue(),
                        first.getMeasurementType(),
                        first.getUnit(),
                        second.getValue(),
                        second.getMeasurementType(),
                        second.getUnit(),
                        "MULTIPLY",
                        result,
                        "SCALAR",
                        "PRODUCT",
                        false,
                        false,
                        null
                );

        jpaRepository.save(entity);

        QuantityDTO response = new QuantityDTO(
                result, "SCALAR", "PRODUCT", false, "MULTIPLY");

        return toResponse(response);
    }

    // Checks that multiplication inputs are valid and use the same category.
    private void validateMultiplicationInputs(
            QuantityInputDTO first,
            QuantityInputDTO second) {

        if (first == null || second == null) {
            throw new QuantityMeasurementException(
                    "Both quantities are required");
        }

        if (first.getMeasurementType() == null
                || first.getMeasurementType().isBlank()
                || first.getUnit() == null
                || first.getUnit().isBlank()
                || second.getMeasurementType() == null
                || second.getMeasurementType().isBlank()
                || second.getUnit() == null
                || second.getUnit().isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement type and unit are required");
        }

        if (!first.getMeasurementType().equalsIgnoreCase(
                second.getMeasurementType())) {
            throw new QuantityMeasurementException(
                    "Both quantities must have the same measurement type");
        }

        if (!Double.isFinite(first.getValue())
                || !Double.isFinite(second.getValue())) {
            throw new QuantityMeasurementException(
                    "Quantity values must be finite");
        }
    }

    public List<QuantityMeasurementDTO> getHistory() {
        return jpaRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "timestamp"))
                .stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .toList();
    }

    public List<QuantityMeasurementDTO> getHistoryByOperation(
            String operation) {
        return jpaRepository
                .findByOperationOrderByTimestampDesc(
                        operation.toUpperCase(Locale.ROOT))
                .stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .toList();
    }

    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(
            String measurementType) {
        return jpaRepository
                .findByFirstMeasurementTypeOrderByTimestampDesc(
                        measurementType.toUpperCase(Locale.ROOT))
                .stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .toList();
    }

    public List<QuantityMeasurementDTO> getErrorHistory() {
        return jpaRepository.findByErrorTrueOrderByTimestampDesc()
                .stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .toList();
    }

    public long countHistory() {
        return jpaRepository.count();
    }
}
