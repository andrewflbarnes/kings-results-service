package org.kingsski.kaas.database.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE  = "Entity %s already exists with [key: %s, value: %s]";

    public EntityAlreadyExistsException(String entity, String field, String value, Throwable e) {
        super(String.format(MESSAGE, entity, field, value), e);
    }
}
