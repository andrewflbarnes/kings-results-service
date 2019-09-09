package org.kingsski.kaas.service.exception;

public abstract class MissingParentException extends EntityConflictException {
    public MissingParentException(String entity, String name, String parentEntity, String parentParam, String parentValue) {
        super(String.format("Cannot create %s %s - missing parent %s with %s %s",
                entity, name, parentEntity, parentParam, parentValue));
    }
}
