
package com.quantitymeasurement.model;

/*
 * Project: Quantity Measurement Application
 * Description: Tests equality, conversion, and addition operations for weight measurements.
 * Author: Hemang
 * Date: 15-11-2026
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    private static final double EPSILON = 1e-5;

    @Test
    void givenSameKilogramValues_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void givenDifferentKilogramValues_whenCompared_shouldReturnFalse() {
        assertNotEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void givenOneKilogramAndThousandGrams_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void givenThousandGramsAndOneKilogram_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void givenEquivalentKilogramAndPoundValues_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    void givenEquivalentGramAndPoundValues_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(453.592, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.POUND)
        );
    }

    @Test
    void givenSamePoundValues_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(2.0, WeightUnit.POUND),
                new QuantityWeight(2.0, WeightUnit.POUND)
        );
    }

    @Test
    void givenDifferentWeightValues_whenCompared_shouldReturnFalse() {
        assertNotEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(3.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void givenNull_whenCompared_shouldReturnFalse() {
        assertNotEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), null);
    }

    @Test
    void givenSameReference_whenCompared_shouldReturnTrue() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(weight, weight);
    }

    @Test
    void givenZeroValuesInDifferentUnits_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(0.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM)
        );
    }

    @Test
    void givenNegativeValuesInDifferentUnits_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(-1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(-1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void givenLargeValuesInDifferentUnits_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(1000.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void givenSmallValuesInDifferentUnits_whenCompared_shouldReturnTrue() {
        assertEquals(
                new QuantityWeight(0.001, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.GRAM)
        );
    }

    @Test
    void givenNullUnit_whenCreated_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void givenNaN_whenCreated_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void givenInfiniteValue_whenCreated_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM));
    }

    @Test
    void givenOneKilogram_whenConvertedToGrams_shouldReturnThousandGrams() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void givenTwoPounds_whenConvertedToKilograms_shouldReturnCorrectValue() {
        QuantityWeight result =
                new QuantityWeight(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(0.907184, result.getValue(), EPSILON);
    }

    @Test
    void givenFiveHundredGrams_whenConvertedToPounds_shouldReturnCorrectValue() {
        QuantityWeight result =
                new QuantityWeight(500.0, WeightUnit.GRAM)
                        .convertTo(WeightUnit.POUND);

        assertEquals(1.102312, result.getValue(), EPSILON);
    }

    @Test
    void givenKilogram_whenConvertedToSameUnit_shouldReturnSameValue() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenZeroKilograms_whenConvertedToGrams_shouldReturnZero() {
        QuantityWeight result =
                new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void givenNegativeKilograms_whenConvertedToGrams_shouldPreserveSign() {
        QuantityWeight result =
                new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    void givenKilogram_whenConvertedToGramsAndBack_shouldPreserveValue() {
        QuantityWeight result =
                new QuantityWeight(1.5, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), 1e-6);
    }

    @Test
    void givenNullTargetUnit_whenConverted_shouldThrowException() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> weight.convertTo(null));
    }

    @Test
    void givenOneAndTwoKilograms_whenAdded_shouldReturnThreeKilograms() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenKilogramAndGram_whenAdded_shouldReturnSumInFirstUnit() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenGramsAndKilograms_whenAdded_shouldReturnSumInGrams() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                new QuantityWeight(0.5, WeightUnit.KILOGRAM)
        );

        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void givenPoundsAndKilograms_whenAdded_shouldReturnSumInPounds() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(2.20462, WeightUnit.POUND),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );

        assertEquals(4.40924, result.getValue(), EPSILON);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    void givenWeight_whenInstanceAddCalled_shouldReturnSum() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = first.add(second);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenKilogramAndGram_whenAddedWithGramTarget_shouldReturnGrams() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void givenPoundAndGram_whenAddedWithPoundTarget_shouldReturnPounds() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.POUND),
                new QuantityWeight(453.592, WeightUnit.GRAM),
                WeightUnit.POUND
        );

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    void givenKilogramAndPounds_whenAddedWithKilogramTarget_shouldReturnKilograms() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                new QuantityWeight(4.0, WeightUnit.POUND),
                WeightUnit.KILOGRAM
        );

        assertEquals(3.814368, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void givenNullFirstWeight_whenAdded_shouldThrowException() {
        QuantityWeight second = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityWeight.add(null, second));
    }

    @Test
    void givenNullSecondWeight_whenAdded_shouldThrowException() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityWeight.add(first, null));
    }

    @Test
    void givenNullTargetUnit_whenAdded_shouldThrowException() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityWeight.add(first, second, null));
    }

    @Test
    void givenWeightAndZero_whenAdded_shouldReturnOriginalMagnitude() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM)
        );

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void givenPositiveAndNegativeWeights_whenAdded_shouldReturnCorrectSum() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(-2000.0, WeightUnit.GRAM)
        );

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void givenLargeWeights_whenAdded_shouldReturnCorrectSum() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1e6, WeightUnit.KILOGRAM),
                new QuantityWeight(1e6, WeightUnit.KILOGRAM)
        );

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void givenSameWeights_whenAdded_shouldNotModifyOriginalObjects() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight.add(first, second);

        assertEquals(1.0, first.getValue(), EPSILON);
        assertEquals(2.0, second.getValue(), EPSILON);
    }

    @Test
    void givenEqualWeights_whenHashed_shouldHaveSameHashCode() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void givenDifferentWeightAndLength_whenCompared_shouldReturnFalse() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    @Test
    void givenOneKilogramAndOneGram_whenCompared_shouldReturnFalse() {
        assertNotEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.GRAM)
        );
    }
}
