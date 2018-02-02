package org.kingsski.api.service.stub;

import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StubTeamService implements TeamService {
    private static final List<DisplayableTeam> TEAMS = Collections.unmodifiableList(new ArrayList<DisplayableTeam>());

    @Override
    public List<DisplayableTeam> getTeamsAll() {
        return TEAMS;
    }

    @Override
    public List<DisplayableTeam> getTeamsByLeague(String league) {
        return TEAMS;
    }

    @Override
    public List<DisplayableTeam> getTeamsByLeagueAndDivision(String league, String division) {
        return TEAMS;
    }

    @Override
    public List<DisplayableTeam> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return TEAMS;
    }
}
