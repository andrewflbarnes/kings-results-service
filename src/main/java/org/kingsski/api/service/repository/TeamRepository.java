package org.kingsski.api.service.repository;

import org.kingsski.api.model.DisplayableTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<DisplayableTeam, Long> {

    List<DisplayableTeam> findByLeague(String league);

    List<DisplayableTeam> findByLeagueAndDivision(String league, String division);
}
