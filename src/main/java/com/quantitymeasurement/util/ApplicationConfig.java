
package com.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Application configuration loader for database and application settings.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class ApplicationConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "application.properties file not found");
            }

            PROPERTIES.load(input);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to load application.properties", exception);
        }
    }

    private ApplicationConfig() {
    }

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null) {
            throw new IllegalArgumentException(
                    "Missing configuration property: " + key);
        }

        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue).trim();
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static long getLongProperty(String key) {
        return Long.parseLong(getProperty(key));
    }
}
