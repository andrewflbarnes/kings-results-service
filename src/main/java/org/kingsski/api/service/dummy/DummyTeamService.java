package org.kingsski.api.service.dummy;

import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A dummy implementation of the {@link TeamService}  interface. This class
 * has a single static list of {@link DisplayableTeam}s which it returns for all
 * method calls.
 *
 * @author Barnesly
 */
public class DummyTeamService implements TeamService {

    private static final List<DisplayableTeam> TEAMS;

    // Static initialiser for the TEAMS variable
    static {
        List<DisplayableTeam> dummyTeams = new ArrayList<DisplayableTeam>();
        dummyTeams.add(createTeam(1, "NORTHERN", "SKUM", "SKUM 1", "M", 20));
        dummyTeams.add(createTeam(2, "NORTHERN", "SKUM", "SKUM 2", "M", 10));
        dummyTeams.add(createTeam(3, "NORTHERN", "SKUM", "SKUM 3", "M", 2));
        dummyTeams.add(createTeam(4, "NORTHERN", "SKUM", "SKUM 4", "M", 0));
        dummyTeams.add(createTeam(5, "NORTHERN", "SKUM", "SKUM 1", "L", 10));
        dummyTeams.add(createTeam(6, "NORTHERN", "SKUM", "SKUM 2", "L", 2));
        dummyTeams.add(createTeam(7, "NORTHERN", "SKUM", "SKUM 1", "B", 10));
        dummyTeams.add(createTeam(8, "NORTHERN", "SKUM", "SKUM 2", "B", 2));

        dummyTeams.add(createTeam(1, "NORTHERN", "Leeds", "Leeds 1", "M", 18));
        dummyTeams.add(createTeam(2, "NORTHERN", "Leeds", "Leeds 2", "M", 14));
        dummyTeams.add(createTeam(3, "NORTHERN", "Leeds", "Leeds 1", "L", 8));
        dummyTeams.add(createTeam(4, "NORTHERN", "Leeds", "Leeds 2", "L", 4));
        dummyTeams.add(createTeam(5, "NORTHERN", "Leeds", "Leeds 1", "B", 6));
        dummyTeams.add(createTeam(6, "NORTHERN", "Leeds", "Leeds 2", "B", 3));

        TEAMS = Collections.unmodifiableList(dummyTeams);
    }

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

    private static DisplayableTeam createTeam(int id, String league, String club, String name, String div, int score) {
        return new DisplayableTeam(name, league, div, id, score, 0, 0, 0);
    }

}
