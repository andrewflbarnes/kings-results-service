package org.kingsski.api.service.repository;

import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryTeamService implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<DisplayableTeam> getTeamsAll() {
        List<DisplayableTeam> teams = new ArrayList<>();
        teamRepository
                .findAll()
                .forEach(t -> {
                    t.updateScores();
                    teams.add(t);
                });
        return teams;
    }

    @Override
    public List<DisplayableTeam> getTeamsByLeague(String league) {
        List<DisplayableTeam> teams = teamRepository.findByLeague(league);
        teams.forEach(DisplayableTeam::updateScores);
        return teams;
    }

    @Override
    public List<DisplayableTeam> getTeamsByLeagueAndDivision(String league, String division) {
        List<DisplayableTeam> teams = teamRepository.findByLeagueAndDivision(league, division);
        teams.forEach(DisplayableTeam::updateScores);
        return teams;
    }

    @Override
    public List<DisplayableTeam> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        List<DisplayableTeam> teams = teamRepository.findByLeagueAndDivision(league, division);
        teams.forEach(DisplayableTeam::updateScores);
        return teams;
    }
}
