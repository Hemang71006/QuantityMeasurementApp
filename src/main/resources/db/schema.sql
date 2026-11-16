
CREATE TABLE IF NOT EXISTS quantity_measurements (
    id VARCHAR(36) PRIMARY KEY,
    timestamp BIGINT NOT NULL,
    first_value DOUBLE PRECISION NOT NULL,
    first_measurement_type VARCHAR(50),
    first_unit VARCHAR(50),
    second_value DOUBLE PRECISION,
    second_measurement_type VARCHAR(50),
    second_unit VARCHAR(50),
    operation VARCHAR(50),
    result_value DOUBLE PRECISION NOT NULL,
    result_measurement_type VARCHAR(50),
    result_unit VARCHAR(50),
    boolean_result BOOLEAN NOT NULL,
    is_error BOOLEAN NOT NULL,
    message VARCHAR(1000)
);

CREATE INDEX IF NOT EXISTS idx_measurement_operation
ON quantity_measurements(operation);

CREATE INDEX IF NOT EXISTS idx_measurement_type
ON quantity_measurements(first_measurement_type);
