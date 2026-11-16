package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.exception.QuantityMeasurementException;
import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.QuantityMeasurementEntity;
import com.quantitymeasurement.model.TemperatureUnit;
import com.quantitymeasurement.model.VolumeUnit;
import com.quantitymeasurement.model.WeightUnit;
import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.repository.QuantityMeasurementCacheRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Implements quantity measurement business operations and repository integration.
 */

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        if (repository == null) {
            throw new QuantityMeasurementException(
                    "Repository cannot be null"
            );
        }

        this.repository = repository;
    }

    public QuantityMeasurementServiceImpl() {
        this(QuantityMeasurementCacheRepository.getInstance());
    }

    @Override
    public QuantityDTO compare(QuantityDTO first, QuantityDTO second) {
        return execute("COMPARE", first, second, null,
                (firstQuantity, secondQuantity, targetUnit) ->
                        new QuantityDTO(
                                first.getValue(),
                                first.getMeasurementType(),
                                first.getUnit(),
                                firstQuantity.equals(secondQuantity),
                                "COMPARE"
                        ));
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {
        return execute("CONVERT", quantity, null, targetUnit,
                (firstQuantity, secondQuantity, resolvedTarget) -> {
                    Quantity<IMeasurable> converted =
                            firstQuantity.convertTo(resolvedTarget);

                    return createQuantityDTO(converted, "CONVERT");
                });
    }

    @Override
    public QuantityDTO add(QuantityDTO first, QuantityDTO second) {
        return execute("ADD", first, second, null,
                (firstQuantity, secondQuantity, targetUnit) -> {
                    Quantity<IMeasurable> result =
                            firstQuantity.add(secondQuantity);

                    return createQuantityDTO(result, "ADD");
                });
    }

    @Override
    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return execute("ADD", first, second, targetUnit,
                (firstQuantity, secondQuantity, resolvedTarget) -> {
                    Quantity<IMeasurable> result =
                            firstQuantity.add(secondQuantity, resolvedTarget);

                    return createQuantityDTO(result, "ADD");
                });
    }

    @Override
    public QuantityDTO subtract(QuantityDTO first, QuantityDTO second) {
        return execute("SUBTRACT", first, second, null,
                (firstQuantity, secondQuantity, targetUnit) -> {
                    Quantity<IMeasurable> result =
                            firstQuantity.subtract(secondQuantity);

                    return createQuantityDTO(result, "SUBTRACT");
                });
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return execute("SUBTRACT", first, second, targetUnit,
                (firstQuantity, secondQuantity, resolvedTarget) -> {
                    Quantity<IMeasurable> result =
                            firstQuantity.subtract(
                                    secondQuantity,
                                    resolvedTarget
                            );

                    return createQuantityDTO(result, "SUBTRACT");
                });
    }

    @Override
    public QuantityDTO divide(QuantityDTO first, QuantityDTO second) {
        return execute("DIVIDE", first, second, null,
                (firstQuantity, secondQuantity, targetUnit) -> {
                    double result = firstQuantity.divide(secondQuantity);

                    return new QuantityDTO(
                            result,
                            "SCALAR",
                            "RATIO",
                            false,
                            "DIVIDE"
                    );
                });
    }

    // Validates inputs, executes the operation and persists its result.
    private QuantityDTO execute(
            String operation,
            QuantityDTO first,
            QuantityDTO second,
            String targetUnitName,
            OperationExecutor executor) {

        try {
            validateDTO(first, "First quantity");

            Quantity<IMeasurable> firstQuantity =
                    createQuantity(first);

            Quantity<IMeasurable> secondQuantity = null;

            if (second != null) {
                validateDTO(second, "Second quantity");
                validateSameMeasurementType(first, second);
                secondQuantity = createQuantity(second);
            }

            IMeasurable targetUnit = null;

            if (targetUnitName != null) {
                targetUnit = resolveUnit(
                        first.getMeasurementType(),
                        targetUnitName
                );
            }

            QuantityDTO result = executor.execute(
                    firstQuantity,
                    secondQuantity,
                    targetUnit
            );

            saveEntity(operation, first, second, result);
            return result;

        } catch (RuntimeException exception) {
            String errorMessage = exception.getMessage();

            if (errorMessage == null || errorMessage.isBlank()) {
                errorMessage = "Unable to perform " + operation;
            }

            QuantityDTO errorDTO = new QuantityDTO(
                    true,
                    errorMessage,
                    operation
            );

            try {
                saveEntity(operation, first, second, errorDTO);
            } catch (RuntimeException persistenceException) {
                return new QuantityDTO(
                        true,
                        errorMessage + "; History persistence failed: "
                                + persistenceException.getMessage(),
                        operation
                );
            }

            return errorDTO;
        }
    }

    // Creates a generic Quantity object from the incoming DTO.
    private Quantity<IMeasurable> createQuantity(QuantityDTO dto) {
        IMeasurable unit = resolveUnit(
                dto.getMeasurementType(),
                dto.getUnit()
        );

        return new Quantity<>(dto.getValue(), unit);
    }

    // Resolves the unit using its measurement category.
    private IMeasurable resolveUnit(
            String measurementType,
            String unitName) {

        if (measurementType == null || measurementType.isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement type cannot be null or blank"
            );
        }

        if (unitName == null || unitName.isBlank()) {
            throw new QuantityMeasurementException(
                    "Unit cannot be null or blank"
            );
        }

        String type = measurementType.trim().toUpperCase(Locale.ROOT);

        try {
            switch (type) {
                case "LENGTH":
                    return LengthUnit.FEET.getUnit(unitName);

                case "WEIGHT":
                    return WeightUnit.KILOGRAM.getUnit(unitName);

                case "VOLUME":
                    return VolumeUnit.LITRE.getUnit(unitName);

                case "TEMPERATURE":
                    return TemperatureUnit.CELSIUS.getUnit(unitName);

                default:
                    throw new QuantityMeasurementException(
                            "Unsupported measurement type: " + measurementType
                    );
            }
        } catch (IllegalArgumentException exception) {
            throw new QuantityMeasurementException(
                    exception.getMessage(),
                    exception
            );
        }
    }

    // Ensures both operands belong to the same measurement category.
    private void validateSameMeasurementType(
            QuantityDTO first,
            QuantityDTO second) {

        if (!first.getMeasurementType().equalsIgnoreCase(
                second.getMeasurementType())) {

            throw new QuantityMeasurementException(
                    "Cannot perform operations on different measurement types"
            );
        }
    }

    // Validates that the incoming DTO contains usable measurement data.
    private void validateDTO(QuantityDTO dto, String fieldName) {
        if (dto == null) {
            throw new QuantityMeasurementException(
                    fieldName + " cannot be null"
            );
        }

        if (dto.getMeasurementType() == null
                || dto.getMeasurementType().isBlank()) {

            throw new QuantityMeasurementException(
                    fieldName + " measurement type cannot be empty"
            );
        }

        if (dto.getUnit() == null || dto.getUnit().isBlank()) {
            throw new QuantityMeasurementException(
                    fieldName + " unit cannot be empty"
            );
        }
    }

    // Converts a generic Quantity result into the standard response DTO.
    private QuantityDTO createQuantityDTO(
            Quantity<IMeasurable> quantity,
            String operation) {

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().getMeasurementType(),
                quantity.getUnit().getUnitName(),
                false,
                operation
        );
    }

    // Stores operation inputs, output and error information.
    private void saveEntity(
            String operation,
            QuantityDTO first,
            QuantityDTO second,
            QuantityDTO result) {

        double firstValue = first == null ? 0.0 : first.getValue();
        String firstType = first == null
                ? null : first.getMeasurementType();
        String firstUnit = first == null
                ? null : first.getUnit();

        Double secondValue = second == null
                ? null : second.getValue();
        String secondType = second == null
                ? null : second.getMeasurementType();
        String secondUnit = second == null
                ? null : second.getUnit();

        boolean isError = result.hasError();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        firstValue,
                        firstType,
                        firstUnit,
                        secondValue,
                        secondType,
                        secondUnit,
                        operation,
                        result.getValue(),
                        result.getMeasurementType(),
                        result.getUnit(),
                        result.isResult(),
                        isError,
                        result.getMessage()
                );

        repository.save(entity);
    }

    @Override
    public List<QuantityDTO> getMeasurementHistory() {
        List<QuantityDTO> history = new ArrayList<>();

        for (QuantityMeasurementEntity entity :
                repository.getAllMeasurements()) {

            if (entity.isError()) {
                history.add(new QuantityDTO(
                        true,
                        entity.getMessage(),
                        entity.getOperation()
                ));
            } else {
                history.add(new QuantityDTO(
                        entity.getResultValue(),
                        entity.getResultMeasurementType(),
                        entity.getResultUnit(),
                        entity.isBooleanResult(),
                        entity.getOperation()
                ));
            }
        }

        return history;
    }

    // Defines the operation executed by this service.
    @FunctionalInterface
    private interface OperationExecutor {

        QuantityDTO execute(
                Quantity<IMeasurable> first,
                Quantity<IMeasurable> second,
                IMeasurable targetUnit
        );
    }
}