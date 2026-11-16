package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;

import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Defines business operations supported by the quantity measurement service.
 */

public interface IQuantityMeasurementService {

    QuantityDTO compare(QuantityDTO first, QuantityDTO second);

    QuantityDTO convert(QuantityDTO quantity, String targetUnit);

    QuantityDTO add(QuantityDTO first, QuantityDTO second);

    QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit
    );

    QuantityDTO subtract(QuantityDTO first, QuantityDTO second);

    QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit
    );

    QuantityDTO divide(QuantityDTO first, QuantityDTO second);

    List<QuantityDTO> getMeasurementHistory();
}