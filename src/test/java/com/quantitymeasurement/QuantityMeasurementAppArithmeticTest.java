
package com.quantitymeasurement;

import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.VolumeUnit;
import com.quantitymeasurement.model.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppArithmeticTest {

    private static final double DELTA = 0.000001;

    @Test
    void givenLengthMeasurements_whenSubtractionDemonstrated_shouldReturnResultInFirstUnit() {

        Quantity<LengthUnit> first =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateSubtraction(
                        first,
                        second
                );

        assertEquals(4.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void givenLengthMeasurements_whenTargetUnitSubtractionDemonstrated_shouldReturnTargetUnitResult() {

        Quantity<LengthUnit> first =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateTargetUnitSubtraction(
                        first,
                        second,
                        LengthUnit.INCHES
                );

        assertEquals(48.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void givenWeightMeasurements_whenSubtractionDemonstrated_shouldReturnResult() {

        Quantity<WeightUnit> first =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateSubtraction(
                        first,
                        second
                );

        assertEquals(4.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenWeightMeasurements_whenTargetUnitSubtractionDemonstrated_shouldReturnTargetUnitResult() {

        Quantity<WeightUnit> first =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateTargetUnitSubtraction(
                        first,
                        second,
                        WeightUnit.GRAM
                );

        assertEquals(4000.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void givenVolumeMeasurements_whenSubtractionDemonstrated_shouldReturnResult() {

        Quantity<VolumeUnit> first =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                QuantityMeasurementApp.demonstrateSubtraction(
                        first,
                        second
                );

        assertEquals(1.5, result.getValue(), DELTA);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    void givenVolumeMeasurements_whenTargetUnitSubtractionDemonstrated_shouldReturnTargetUnitResult() {

        Quantity<VolumeUnit> first =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                QuantityMeasurementApp.demonstrateTargetUnitSubtraction(
                        first,
                        second,
                        VolumeUnit.MILLILITRE
                );

        assertEquals(1500.0, result.getValue(), DELTA);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void givenLengthMeasurements_whenDivisionDemonstrated_shouldReturnRatio() {

        Quantity<LengthUnit> first =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result =
                QuantityMeasurementApp.demonstrateDivision(
                        first,
                        second
                );

        assertEquals(5.0, result, DELTA);
    }

    @Test
    void givenDifferentLengthUnits_whenDivisionDemonstrated_shouldReturnCorrectRatio() {

        Quantity<LengthUnit> first =
                new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<>(12.0, LengthUnit.INCHES);

        double result =
                QuantityMeasurementApp.demonstrateDivision(
                        first,
                        second
                );

        assertEquals(2.0, result, DELTA);
    }

    @Test
    void givenWeightMeasurements_whenDivisionDemonstrated_shouldReturnRatio() {

        Quantity<WeightUnit> first =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> second =
                new Quantity<>(2000.0, WeightUnit.GRAM);

        double result =
                QuantityMeasurementApp.demonstrateDivision(
                        first,
                        second
                );

        assertEquals(5.0, result, DELTA);
    }

    @Test
    void givenVolumeMeasurements_whenDivisionDemonstrated_shouldReturnRatio() {

        Quantity<VolumeUnit> first =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        double result =
                QuantityMeasurementApp.demonstrateDivision(
                        first,
                        second
                );

        assertEquals(4.0, result, DELTA);
    }

    @Test
    void givenNullMeasurements_whenSubtractionDemonstrated_shouldThrowException() {

        Quantity<LengthUnit> measurement =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateSubtraction(
                        measurement,
                        null
                )
        );
    }

    @Test
    void givenNullMeasurements_whenDivisionDemonstrated_shouldThrowException() {

        Quantity<LengthUnit> measurement =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateDivision(
                        measurement,
                        null
                )
        );
    }
}
