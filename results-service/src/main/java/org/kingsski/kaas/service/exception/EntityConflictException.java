package org.kingsski.kaas.service.exception;

public abstract class EntityConflictException extends Exception {
    public EntityConflictException(String message) {
        super(message);
    }
}
