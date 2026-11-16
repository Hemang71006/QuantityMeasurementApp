
package com.quantitymeasurement;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.model.LengthUnit;
import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.model.QuantityLength;
import com.quantitymeasurement.model.TemperatureUnit;
import com.quantitymeasurement.model.VolumeUnit;
import com.quantitymeasurement.model.WeightUnit;
import com.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.quantitymeasurement.repository.QuantityMeasurementRepositoryFactory;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;

/*
 * Project Name : Quantity Measurement App
 * Module : UC16 - Database Integration with JDBC
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Application entry point and facade for quantity measurement operations.
 */
public class QuantityMeasurementApp {

    private static final QuantityMeasurementApp INSTANCE =
            new QuantityMeasurementApp();

    private final QuantityMeasurementController controller;
    private final IQuantityMeasurementRepository repository;

    private QuantityMeasurementApp() {
        repository =
                QuantityMeasurementRepositoryFactory.createRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    // Returns the singleton application instance.
    public static QuantityMeasurementApp getInstance() {
        return INSTANCE;
    }

    // Returns the application controller.
    public QuantityMeasurementController getController() {
        return controller;
    }

    // Releases application resources.
    public void shutdown() {
        repository.releaseResources();
    }

    // Maintains compatibility with the UC1 Feet implementation.
    public static final class Feet {

        private final QuantityLength quantity;

        public Feet(double value) {
            quantity = new QuantityLength(value, LengthUnit.FEET);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;
            return quantity.equals(other.quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    // Maintains compatibility with the UC2 Inches implementation.
    public static final class Inches {

        private final QuantityLength quantity;

        public Inches(double value) {
            quantity = new QuantityLength(value, LengthUnit.INCHES);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Inches other = (Inches) obj;
            return quantity.equals(other.quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    // Compares two feet measurements.
    public static boolean areFeetEqual(double first, double second) {
        return new QuantityLength(first, LengthUnit.FEET)
                .equals(new QuantityLength(second, LengthUnit.FEET));
    }

    // Compares two inches measurements.
    public static boolean areInchesEqual(double first, double second) {
        return new QuantityLength(first, LengthUnit.INCHES)
                .equals(new QuantityLength(second, LengthUnit.INCHES));
    }

    // Compares feet and inches measurements.
    public static boolean areFeetAndInchesEqual(
            double feetValue,
            double inchesValue) {

        return new QuantityLength(feetValue, LengthUnit.FEET)
                .equals(new QuantityLength(inchesValue, LengthUnit.INCHES));
    }

    // Displays a quantity without requiring its measurement category.
    public static void displayMeasurement(Quantity<?> measurement) {
        if (measurement == null) {
            throw new IllegalArgumentException(
                    "Measurement cannot be null"
            );
        }

        System.out.println("Measurement: " + measurement);
    }

    // Demonstrates equality for any two generic quantities.
    public static boolean demonstrateEquality(
            Quantity<?> first,
            Quantity<?> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        boolean result = first.equals(second);

        System.out.println(
                first + " and " + second + " are equal: " + result
        );

        return result;
    }

    // Demonstrates conversion for any supported measurement category.
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> measurement,
            U targetUnit) {

        if (measurement == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Measurement and target unit cannot be null"
            );
        }

        Quantity<U> result = measurement.convertTo(targetUnit);

        System.out.println(measurement + " = " + result);

        return result;
    }

    // Demonstrates addition using the first measurement's unit.
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> first,
            Quantity<U> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        Quantity<U> result = first.add(second);

        System.out.println(first + " + " + second + " = " + result);

        return result;
    }

    // Demonstrates addition using an explicitly selected target unit.
    public static <U extends IMeasurable>
    Quantity<U> demonstrateTargetUnitAddition(
            Quantity<U> first,
            Quantity<U> second,
            U targetUnit) {

        if (first == null || second == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Measurements and target unit cannot be null"
            );
        }

        Quantity<U> result = first.add(second, targetUnit);

        System.out.println(
                first + " + " + second
                        + " = " + result
                        + " (Target Unit: " + targetUnit.getUnitName() + ")"
        );

        return result;
    }

    // Demonstrates subtraction using the first measurement's unit.
    public static <U extends IMeasurable>
    Quantity<U> demonstrateSubtraction(
            Quantity<U> first,
            Quantity<U> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        Quantity<U> result = first.subtract(second);

        System.out.println(first + " - " + second + " = " + result);

        return result;
    }

    // Demonstrates subtraction using an explicitly selected target unit.
    public static <U extends IMeasurable>
    Quantity<U> demonstrateTargetUnitSubtraction(
            Quantity<U> first,
            Quantity<U> second,
            U targetUnit) {

        if (first == null || second == null || targetUnit == null) {
            throw new IllegalArgumentException(
                    "Measurements and target unit cannot be null"
            );
        }

        Quantity<U> result = first.subtract(second, targetUnit);

        System.out.println(
                first + " - " + second
                        + " = " + result
                        + " (Target Unit: " + targetUnit.getUnitName() + ")"
        );

        return result;
    }

    // Demonstrates division and returns a dimensionless ratio.
    public static <U extends IMeasurable>
    double demonstrateDivision(
            Quantity<U> first,
            Quantity<U> second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null"
            );
        }

        double result = first.divide(second);

        System.out.println(first + " / " + second + " = " + result);

        return result;
    }

    // Demonstrates unsupported temperature arithmetic.
    private static void demonstrateUnsupportedTemperatureOperations(
            Quantity<TemperatureUnit> first,
            Quantity<TemperatureUnit> second) {

        try {
            demonstrateAddition(first, second);
        } catch (UnsupportedOperationException exception) {
            System.out.println("Addition: " + exception.getMessage());
        }

        try {
            demonstrateSubtraction(first, second);
        } catch (UnsupportedOperationException exception) {
            System.out.println("Subtraction: " + exception.getMessage());
        }

        try {
            demonstrateDivision(first, second);
        } catch (UnsupportedOperationException exception) {
            System.out.println("Division: " + exception.getMessage());
        }
    }

    // Demonstrates operations through the controller and service layers.
    private void demonstrateControllerOperations() {

        System.out.println("\nUC16 - JDBC Database Integration Demonstration");
        System.out.println("-----------------------------------------------");

        System.out.println("\nLength:");

        QuantityDTO oneFoot =
                new QuantityDTO(1.0, "LENGTH", "FEET");

        QuantityDTO twelveInches =
                new QuantityDTO(12.0, "LENGTH", "INCHES");

        controller.compare(oneFoot, twelveInches);

        controller.convert(oneFoot, "INCHES");

        controller.add(oneFoot, twelveInches);

        controller.add(oneFoot, twelveInches, "INCHES");

        controller.subtract(
                new QuantityDTO(5.0, "LENGTH", "FEET"),
                twelveInches
        );

        controller.subtract(
                new QuantityDTO(5.0, "LENGTH", "FEET"),
                twelveInches,
                "INCHES"
        );

        controller.divide(
                new QuantityDTO(10.0, "LENGTH", "FEET"),
                new QuantityDTO(2.0, "LENGTH", "FEET")
        );

        System.out.println("\nWeight:");

        QuantityDTO oneKilogram =
                new QuantityDTO(1.0, "WEIGHT", "KILOGRAM");

        QuantityDTO thousandGrams =
                new QuantityDTO(1000.0, "WEIGHT", "GRAM");

        controller.compare(oneKilogram, thousandGrams);
        controller.convert(oneKilogram, "GRAM");
        controller.add(oneKilogram, thousandGrams);
        controller.add(oneKilogram, thousandGrams, "GRAM");

        System.out.println("\nVolume:");

        QuantityDTO oneLitre =
                new QuantityDTO(1.0, "VOLUME", "LITRE");

        QuantityDTO fiveHundredMillilitres =
                new QuantityDTO(500.0, "VOLUME", "MILLILITRE");

        controller.compare(
                oneLitre,
                new QuantityDTO(1000.0, "VOLUME", "MILLILITRE")
        );

        controller.convert(oneLitre, "MILLILITRE");
        controller.add(oneLitre, fiveHundredMillilitres);

        System.out.println("\nTemperature:");

        QuantityDTO zeroCelsius =
                new QuantityDTO(0.0, "TEMPERATURE", "CELSIUS");

        QuantityDTO thirtyTwoFahrenheit =
                new QuantityDTO(32.0, "TEMPERATURE", "FAHRENHEIT");

        controller.compare(zeroCelsius, thirtyTwoFahrenheit);
        controller.convert(zeroCelsius, "KELVIN");

        controller.add(
                zeroCelsius,
                new QuantityDTO(100.0, "TEMPERATURE", "CELSIUS")
        );

        System.out.println("\nPersisted Measurement History:");

        controller.getMeasurementHistory().forEach(
                System.out::println
        );
    }

    // Application entry point.
    public static void main(String[] args) {

        QuantityMeasurementApp app = getInstance();

        System.out.println("Quantity Measurement App - UC16");
        System.out.println("--------------------------------");

        try {
            app.demonstrateControllerOperations();

            System.out.println(
                    "\nPrevious UC1-UC14 Compatibility Demonstrations:"
            );

            Quantity<LengthUnit> oneFoot =
                    new Quantity<>(1.0, LengthUnit.FEET);

            Quantity<LengthUnit> twelveInches =
                    new Quantity<>(12.0, LengthUnit.INCHES);

            demonstrateEquality(oneFoot, twelveInches);

            demonstrateConversion(oneFoot, LengthUnit.INCHES);

            demonstrateAddition(oneFoot, twelveInches);

            demonstrateTargetUnitAddition(
                    oneFoot,
                    twelveInches,
                    LengthUnit.INCHES
            );

            demonstrateSubtraction(
                    new Quantity<>(5.0, LengthUnit.FEET),
                    new Quantity<>(12.0, LengthUnit.INCHES)
            );

            demonstrateTargetUnitSubtraction(
                    new Quantity<>(5.0, LengthUnit.FEET),
                    new Quantity<>(12.0, LengthUnit.INCHES),
                    LengthUnit.INCHES
            );

            demonstrateDivision(
                    new Quantity<>(10.0, LengthUnit.FEET),
                    new Quantity<>(2.0, LengthUnit.FEET)
            );

            Quantity<WeightUnit> oneKilogram =
                    new Quantity<>(1.0, WeightUnit.KILOGRAM);

            Quantity<WeightUnit> thousandGrams =
                    new Quantity<>(1000.0, WeightUnit.GRAM);

            demonstrateEquality(oneKilogram, thousandGrams);
            demonstrateConversion(oneKilogram, WeightUnit.GRAM);
            demonstrateAddition(oneKilogram, thousandGrams);

            demonstrateTargetUnitAddition(
                    oneKilogram,
                    thousandGrams,
                    WeightUnit.GRAM
            );

            Quantity<VolumeUnit> oneLitre =
                    new Quantity<>(1.0, VolumeUnit.LITRE);

            demonstrateEquality(
                    oneLitre,
                    new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
            );

            demonstrateConversion(oneLitre, VolumeUnit.MILLILITRE);

            demonstrateAddition(
                    oneLitre,
                    new Quantity<>(500.0, VolumeUnit.MILLILITRE)
            );

            Quantity<TemperatureUnit> zeroCelsius =
                    new Quantity<>(0.0, TemperatureUnit.CELSIUS);

            Quantity<TemperatureUnit> thirtyTwoFahrenheit =
                    new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

            demonstrateEquality(zeroCelsius, thirtyTwoFahrenheit);

            demonstrateConversion(
                    zeroCelsius,
                    TemperatureUnit.KELVIN
            );

            demonstrateUnsupportedTemperatureOperations(
                    zeroCelsius,
                    new Quantity<>(100.0, TemperatureUnit.CELSIUS)
            );

            System.out.println("\nWildcard Demonstration:");

            displayMeasurement(oneFoot);
            displayMeasurement(oneKilogram);
            displayMeasurement(oneLitre);
            displayMeasurement(zeroCelsius);

            System.out.println("\nDifferent Category Comparison:");

            System.out.println(
                    "1 KILOGRAM = 1 FOOT: "
                            + oneKilogram.equals(oneFoot)
            );

        } finally {
            app.shutdown();
        }
    }
}
