package org.kingsski.kaas.service.team;

import org.kingsski.kaas.service.exception.AlreadyExistsException;

public class TeamAlreadyExistsException extends AlreadyExistsException {
    public TeamAlreadyExistsException(String type, String value) {
        super("team", type, value);
    }
}
