package org.kingsski.kaas.database.exception;

public class EntityConstraintViolationException extends RuntimeException {

    private static final String MESSAGE  = "Entity %s constraint violated: %s";

    public EntityConstraintViolationException(String entity, Throwable e) {
        super(String.format(MESSAGE, entity, e.getMessage()), e);
    }
}
