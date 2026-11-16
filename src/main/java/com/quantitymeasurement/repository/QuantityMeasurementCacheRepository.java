package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Project Name : Quantity Measurement App
 * Module : UC15 - N-Tier Architecture Refactoring
 * Author : Hemang
 * Date : 15-11-2026
 * Description : Singleton repository that maintains an in-memory cache and persists measurement history.
 */

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static final String FILE_NAME = "quantity-measurement-history.dat";

    private static final QuantityMeasurementCacheRepository INSTANCE =
            new QuantityMeasurementCacheRepository();

    private final List<QuantityMeasurementEntity> measurements;
    private final File storageFile;

    private QuantityMeasurementCacheRepository() {
        measurements = new ArrayList<>();
        storageFile = new File(FILE_NAME);
        loadFromFile();
    }

    // Returns the single repository instance.
    public static QuantityMeasurementCacheRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "Measurement entity cannot be null"
            );
        }

        measurements.add(entity);
        appendToFile(entity);
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> getAllMeasurements() {
        return Collections.unmodifiableList(
                new ArrayList<>(measurements)
        );
    }

    // Loads previously stored entities into the in-memory cache.
    private void loadFromFile() {
        if (!storageFile.exists() || storageFile.length() == 0) {
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(storageFile))) {

            while (true) {
                Object object = inputStream.readObject();

                if (object instanceof QuantityMeasurementEntity entity) {
                    measurements.add(entity);
                }
            }

        } catch (EOFException exception) {
            // Reaching the end of the file is expected.
        } catch (IOException | ClassNotFoundException exception) {
            throw new IllegalStateException(
                    "Unable to load measurement history",
                    exception
            );
        }
    }

    // Appends one entity without overwriting existing serialized records.
    private void appendToFile(QuantityMeasurementEntity entity) {
        boolean fileExists = storageFile.exists()
                && storageFile.length() > 0;

        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(storageFile, true);

             ObjectOutputStream outputStream = fileExists
                     ? new AppendableObjectOutputStream(fileOutputStream)
                     : new ObjectOutputStream(fileOutputStream)) {

            outputStream.writeObject(entity);
            outputStream.flush();

        } catch (IOException exception) {
            measurements.remove(entity);

            throw new IllegalStateException(
                    "Unable to save measurement history",
                    exception
            );
        }
    }

    // Prevents writing a second serialization header when appending records.
    private static class AppendableObjectOutputStream
            extends ObjectOutputStream {

        private AppendableObjectOutputStream(FileOutputStream outputStream)
                throws IOException {
            super(outputStream);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }


    @Override
    public List<QuantityMeasurementEntity> getByOperationType(String operation) {
        return getAllMeasurements().stream()
                .filter(entity -> entity.getOperation() != null
                        && entity.getOperation().equalsIgnoreCase(operation))
                .toList();
    }

    @Override
    public List<QuantityMeasurementEntity> getByMeasurementType(String measurementType) {
        return getAllMeasurements().stream()
                .filter(entity -> entity.getFirstMeasurementType() != null
                        && entity.getFirstMeasurementType()
                        .equalsIgnoreCase(measurementType))
                .toList();
    }

    @Override
    public long count() {
        return getAllMeasurements().size();
    }


    @Override
    public synchronized void deleteAll() {
        measurements.clear();

        try (FileOutputStream outputStream =
                     new FileOutputStream(storageFile, false)) {
            outputStream.flush();
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Failed to clear measurement history", exception);
        }
    }


}