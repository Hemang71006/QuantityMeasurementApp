
package com.quantitymeasurement.repository;

import com.quantitymeasurement.util.ApplicationConfig;

/*
 * Creates the configured quantity measurement repository.
 * Author: Hemang
 * Date: 15-11-2026
 */
public final class QuantityMeasurementRepositoryFactory {

    private QuantityMeasurementRepositoryFactory() {
    }

    // Creates a repository using the application configuration.
    public static IQuantityMeasurementRepository createRepository() {
        String repositoryType = ApplicationConfig.getProperty(
                "app.repository.type", "cache"
        );

        return createRepository(repositoryType);
    }

    // Creates a repository based on the supplied repository type.
    public static IQuantityMeasurementRepository createRepository(
            String repositoryType) {

        if (repositoryType == null || repositoryType.isBlank()) {
            throw new IllegalArgumentException(
                    "Repository type cannot be null or empty"
            );
        }

        return switch (repositoryType.trim().toLowerCase()) {
            case "database", "jdbc", "h2" ->
                    new QuantityMeasurementDatabaseRepository();

            case "cache" ->
                    QuantityMeasurementCacheRepository.getInstance();

            default -> throw new IllegalArgumentException(
                    "Unsupported repository type: " + repositoryType
            );
        };
    }
}
