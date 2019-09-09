package org.kingsski.kaas.service.team;

import org.kingsski.kaas.service.exception.MissingParentException;

public class TeamMissingParentException extends MissingParentException {
    public static TeamMissingParentException ofClub(String teamName, String clubParam, String clubValue) {
        return new TeamMissingParentException(teamName, "club", clubParam, clubValue);
    }

    public TeamMissingParentException(String teamName, String parentEntity, String parentParam, String parentValue) {
        super("team", teamName, parentEntity, parentParam, parentValue);
    }
}
