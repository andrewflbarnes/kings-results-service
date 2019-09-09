package org.kingsski.kaas.service.club;

import org.kingsski.kaas.service.exception.AlreadyExistsException;

public class ClubAlreadyExistsException extends AlreadyExistsException {
    public ClubAlreadyExistsException(String type, String value) {
        super("club", type, value);
    }
}
