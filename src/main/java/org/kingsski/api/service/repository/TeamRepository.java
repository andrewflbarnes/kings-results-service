package org.kingsski.api.service.repository;

import org.kingsski.api.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findByLeague(String league);

    List<Team> findByLeagueAndDivision(String league, String division);
}
