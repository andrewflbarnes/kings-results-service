package org.kingsski.api.dao.team;

import org.kingsski.api.model.Team;
import org.kingsski.api.dao.team.TeamDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StubTeamDao implements TeamDao {
    private static final List<Team> TEAMS = Collections.unmodifiableList(new ArrayList<Team>());

    @Override
    public List<Team> getTeamsAll() {
        return TEAMS;
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        return TEAMS;
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return TEAMS;
    }

    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return TEAMS;
    }
}
