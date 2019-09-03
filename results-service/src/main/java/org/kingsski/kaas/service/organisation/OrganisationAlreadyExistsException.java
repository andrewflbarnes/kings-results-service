package org.kingsski.kaas.service.organisation;

import org.kingsski.kaas.service.exception.AlreadyExistsException;

public class OrganisationAlreadyExistsException extends AlreadyExistsException {
    public OrganisationAlreadyExistsException(String type, String value) {
        super("entity", type, value);
    }
}
