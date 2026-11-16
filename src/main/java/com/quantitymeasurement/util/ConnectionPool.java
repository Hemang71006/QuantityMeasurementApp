
package com.quantitymeasurement.util;

import com.quantitymeasurement.exception.DatabaseException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/*
 * Manages JDBC database connections using HikariCP.
 * Initializes the database schema and provides connection pool statistics.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class ConnectionPool {

    private static final HikariDataSource DATA_SOURCE;

    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setDriverClassName(
                    ApplicationConfig.getProperty("db.driver"));
            config.setJdbcUrl(
                    ApplicationConfig.getProperty("db.url"));
            config.setUsername(
                    ApplicationConfig.getProperty("db.username"));
            config.setPassword(
                    ApplicationConfig.getProperty("db.password"));

            config.setMaximumPoolSize(
                    ApplicationConfig.getIntProperty(
                            "db.pool.maximum-pool-size"));

            config.setMinimumIdle(
                    ApplicationConfig.getIntProperty(
                            "db.pool.minimum-idle"));

            config.setConnectionTimeout(
                    ApplicationConfig.getLongProperty(
                            "db.pool.connection-timeout"));

            config.setIdleTimeout(
                    ApplicationConfig.getLongProperty(
                            "db.pool.idle-timeout"));

            config.setMaxLifetime(
                    ApplicationConfig.getLongProperty(
                            "db.pool.max-lifetime"));

            config.setPoolName("QuantityMeasurementPool");

            DATA_SOURCE = new HikariDataSource(config);

            initializeSchema();

        } catch (Exception exception) {
            throw new DatabaseException(
                    "Failed to initialize database connection pool",
                    exception);
        }
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    public static HikariDataSource getDataSource() {
        return DATA_SOURCE;
    }

    private static void initializeSchema() {
        try (InputStream input = ConnectionPool.class
                .getClassLoader()
                .getResourceAsStream("db/schema.sql")) {

            if (input == null) {
                throw new DatabaseException(
                        "Database schema file not found");
            }

            String schema = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {

                for (String sql : schema.split(";")) {
                    if (!sql.isBlank()) {
                        statement.execute(sql.trim());
                    }
                }
            }

        } catch (IOException | SQLException exception) {
            throw new DatabaseException(
                    "Failed to initialize database schema", exception);
        }
    }

    public static String getPoolStatistics() {
        var pool = DATA_SOURCE.getHikariPoolMXBean();

        if (pool == null) {
            return "Connection pool statistics unavailable";
        }

        return "Active Connections: " + pool.getActiveConnections()
                + ", Idle Connections: " + pool.getIdleConnections()
                + ", Total Connections: " + pool.getTotalConnections()
                + ", Threads Awaiting Connection: "
                + pool.getThreadsAwaitingConnection();
    }


    public static synchronized void shutdown() {
        if (!DATA_SOURCE.isClosed()) {
            DATA_SOURCE.close();
        }
    }

}
