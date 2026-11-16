
package com.quantitymeasurement.repository;

import com.quantitymeasurement.exception.DatabaseException;
import com.quantitymeasurement.model.QuantityMeasurementEntity;
import com.quantitymeasurement.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * JDBC repository for persistent quantity measurement history.
 * Author: Hemang
 * Date: 15-11-2026
 */
public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurements (" +
                    "id, timestamp, first_value, first_measurement_type, first_unit, " +
                    "second_value, second_measurement_type, second_unit, operation, " +
                    "result_value, result_measurement_type, result_unit, " +
                    "boolean_result, is_error, message) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM quantity_measurements ORDER BY timestamp DESC";

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = INSERT_SQL;

        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, entity.getId());
                statement.setLong(2, entity.getTimestamp());
                statement.setDouble(3, entity.getFirstValue());
                statement.setString(4, entity.getFirstMeasurementType());
                statement.setString(5, entity.getFirstUnit());

                if (entity.getSecondValue() == null) {
                    statement.setNull(6, java.sql.Types.DOUBLE);
                } else {
                    statement.setDouble(6, entity.getSecondValue());
                }

                statement.setString(7, entity.getSecondMeasurementType());
                statement.setString(8, entity.getSecondUnit());
                statement.setString(9, entity.getOperation());
                statement.setDouble(10, entity.getResultValue());
                statement.setString(11, entity.getResultMeasurementType());
                statement.setString(12, entity.getResultUnit());
                statement.setBoolean(13, entity.isBooleanResult());
                statement.setBoolean(14, entity.isError());
                statement.setString(15, entity.getMessage());

                statement.executeUpdate();
                connection.commit();

                logger.info("Measurement saved with ID: {}", entity.getId());

            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            }

        } catch (SQLException exception) {
            logger.error("Unable to save measurement", exception);
            throw new DatabaseException(
                    "Unable to save measurement", exception);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        List<QuantityMeasurementEntity> measurements = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                measurements.add(mapEntity(resultSet));
            }

        } catch (SQLException exception) {
            logger.error("Unable to retrieve measurements", exception);
            throw new DatabaseException(
                    "Unable to retrieve measurements", exception);
        }

        return measurements;
    }

    @Override
    public List<QuantityMeasurementEntity> getByOperationType(
            String operation) {

        return findByColumn("operation", operation);
    }

    @Override
    public List<QuantityMeasurementEntity> getByMeasurementType(
            String measurementType) {

        return findByColumn("first_measurement_type", measurementType);
    }

    private List<QuantityMeasurementEntity> findByColumn(
            String column, String value) {

        if (!column.equals("operation")
                && !column.equals("first_measurement_type")) {
            throw new IllegalArgumentException("Invalid filter column");
        }

        String sql = "SELECT * FROM quantity_measurements WHERE "
                + column + " = ? ORDER BY timestamp DESC";

        List<QuantityMeasurementEntity> measurements = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, value);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    measurements.add(mapEntity(resultSet));
                }
            }

        } catch (SQLException exception) {
            throw new DatabaseException(
                    "Unable to filter measurements", exception);
        }

        return measurements;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM quantity_measurements";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            return resultSet.next() ? resultSet.getLong(1) : 0;

        } catch (SQLException exception) {
            throw new DatabaseException(
                    "Unable to count measurements", exception);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurements";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
            logger.info("All measurement records deleted");

        } catch (SQLException exception) {
            throw new DatabaseException(
                    "Unable to delete measurements", exception);
        }
    }

    @Override
    public String getPoolStatistics() {
        return ConnectionPool.getPoolStatistics();
    }

    @Override
    public void releaseResources() {
        ConnectionPool.shutdown();
    }

    private QuantityMeasurementEntity mapEntity(ResultSet resultSet)
            throws SQLException {

        double secondValue = resultSet.getDouble("second_value");
        Double nullableSecondValue = resultSet.wasNull()
                ? null : secondValue;

        return new QuantityMeasurementEntity(
                resultSet.getString("id"),
                resultSet.getLong("timestamp"),
                resultSet.getDouble("first_value"),
                resultSet.getString("first_measurement_type"),
                resultSet.getString("first_unit"),
                nullableSecondValue,
                resultSet.getString("second_measurement_type"),
                resultSet.getString("second_unit"),
                resultSet.getString("operation"),
                resultSet.getDouble("result_value"),
                resultSet.getString("result_measurement_type"),
                resultSet.getString("result_unit"),
                resultSet.getBoolean("boolean_result"),
                resultSet.getBoolean("is_error"),
                resultSet.getString("message")
        );
    }
}
