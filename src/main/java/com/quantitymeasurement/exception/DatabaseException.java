
package com.quantitymeasurement.exception;

/*
 * Custom exception for database operations.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
