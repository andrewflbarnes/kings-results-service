package org.kingsski.api.service.repository;

import org.kingsski.api.model.WordpressTeam;
import org.kingsski.api.model.WordpressTeamPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<WordpressTeam, WordpressTeamPK> {

    List<WordpressTeam> findByWordpressTeamPK_League(String league);

    List<WordpressTeam> findByWordpressTeamPK_LeagueAndWordpressTeamPK_Division(String league, String division);
}
