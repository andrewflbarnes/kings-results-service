package org.kingsski.api.dao.team;

import org.kingsski.api.model.Team;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTeamDao implements TeamDao {

    private TeamRepository teamRepository;

    public RepositoryTeamDao(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getTeamsAll() {
        List<Team> teams = new ArrayList<>();
        teamRepository
                .findAll()
                .forEach(t -> {
                    t.updateScores();
                    teams.add(t);
                });
        return teams;
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        List<Team> teams = teamRepository.findByLeague(league);
        teams.forEach(Team::updateScores);
        return teams;
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        List<Team> teams = teamRepository.findByLeagueAndDivision(league, division);
        teams.forEach(Team::updateScores);
        return teams;
    }

    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        List<Team> teams = teamRepository.findByLeagueAndDivision(league, division);
        teams.forEach(Team::updateScores);
        return teams;
    }
}
