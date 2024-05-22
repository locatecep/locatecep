package io.github.locatecep.service.exception;

public class NotFoundException extends CepServiceException {
    public NotFoundException(String message) {
        super(message);
    }
}
