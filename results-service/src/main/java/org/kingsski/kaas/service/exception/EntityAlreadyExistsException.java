package org.kingsski.kaas.service.exception;

public class EntityAlreadyExistsException extends EntityConflictException {
    public EntityAlreadyExistsException(String entity, String type, String value) {
        super(String.format("%s already exists with %s %s", entity, type, value));
    }
}
