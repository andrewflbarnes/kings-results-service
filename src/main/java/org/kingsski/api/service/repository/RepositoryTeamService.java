package org.kingsski.api.service.repository;

import org.kingsski.api.service.TeamService;
import org.kingsski.wax.data.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryTeamService implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getTeamsAll() {
        List<Team> teams = new ArrayList<>();
        teamRepository
                .findAll()
                .forEach(t -> teams.add(t.asTeam()));
        return teams;
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        List<Team> teams = new ArrayList<>();
        teamRepository
                .findByWordpressTeamPK_League(league)
                .forEach(t -> teams.add(t.asTeam()));
        return teams;
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        List<Team> teams = new ArrayList<>();
        teamRepository
                .findByWordpressTeamPK_LeagueAndWordpressTeamPK_Division(league, division)
                .forEach(t -> teams.add(t.asTeam()));
        return teams;
    }

    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        List<Team> teams = new ArrayList<>();
        teamRepository
                .findByWordpressTeamPK_LeagueAndWordpressTeamPK_Division(league, division)
                .forEach(t -> teams.add(t.asTeam()));
        return teams;
    }
}
