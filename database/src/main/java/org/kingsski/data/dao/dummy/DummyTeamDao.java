package org.kingsski.data.dao.dummy;

import org.kingsski.data.dao.TeamDao;
import org.kingsski.data.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A dummy implementation of the {@link TeamDao}  interface. This class
 * has a single static list of {@link Team}s which it returns for all
 * method calls.
 *
 * @author Barnesly
 */
public class DummyTeamDao implements TeamDao {

    private static final List<Team> TEAMS;
    private static final String NORTHERN = "NORTHERN";
    private static final String SKUM = "SKUM";
    private static final String MIXED = "M";
    private static final String SKUM1 = "SKUM 1";
    private static final String SKUM2 = "SKUM 2";
    private static final String SKKUM3 = "SKUM 3";
    private static final String SKUM4 = "SKUM 4";
    private static final String LADIES = "L";
    private static final String BOARD = "B";
    private static final String LEEDS = "Leeds";
    private static final String LEEDS1 = "Leeds 1";
    private static final String LEEDS2 = "Leeds 2";

    // Static initialiser for the TEAMS variable
    static {
        List<Team> dummyTeams = new ArrayList<>();
        dummyTeams.add(createTeam(1, NORTHERN, SKUM, SKUM1, MIXED, 20));
        dummyTeams.add(createTeam(2, NORTHERN, SKUM, SKUM2, MIXED, 10));
        dummyTeams.add(createTeam(3, NORTHERN, SKUM, SKKUM3, MIXED, 2));
        dummyTeams.add(createTeam(4, NORTHERN, SKUM, SKUM4, MIXED, 0));
        dummyTeams.add(createTeam(5, NORTHERN, SKUM, SKUM1, LADIES, 10));
        dummyTeams.add(createTeam(6, NORTHERN, SKUM, SKUM2, LADIES, 2));
        dummyTeams.add(createTeam(7, NORTHERN, SKUM, SKUM1, BOARD, 10));
        dummyTeams.add(createTeam(8, NORTHERN, SKUM, SKUM2, BOARD, 2));

        dummyTeams.add(createTeam(1, NORTHERN, LEEDS, LEEDS1, MIXED, 18));
        dummyTeams.add(createTeam(2, NORTHERN, LEEDS, LEEDS2, MIXED, 14));
        dummyTeams.add(createTeam(3, NORTHERN, LEEDS, LEEDS1, LADIES, 8));
        dummyTeams.add(createTeam(4, NORTHERN, LEEDS, LEEDS2, LADIES, 4));
        dummyTeams.add(createTeam(5, NORTHERN, LEEDS, LEEDS1, BOARD, 6));
        dummyTeams.add(createTeam(6, NORTHERN, LEEDS, LEEDS2, BOARD, 3));

        TEAMS = Collections.unmodifiableList(dummyTeams);
    }

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

    private static Team createTeam(int id, String league, String club, String name, String div, int score) {
        return new Team(name, league, div, id, score, 0, 0, 0);
    }

}
