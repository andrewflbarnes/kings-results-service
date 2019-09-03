package org.kingsski.kaas.service.exception;

public abstract class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String entity, String type, String value) {
        super(String.format("%s already exists with %s %s", entity, type, value));
    }
}
