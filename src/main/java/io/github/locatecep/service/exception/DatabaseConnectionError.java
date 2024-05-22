package io.github.locatecep.service.exception;

public class DatabaseConnectionError extends CepServiceException {
    public DatabaseConnectionError(String message) {
        super(message);
    }
}
