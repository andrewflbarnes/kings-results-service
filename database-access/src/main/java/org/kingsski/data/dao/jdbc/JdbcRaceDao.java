package org.kingsski.data.dao.jdbc;

import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.jdbc.mapper.RaceMapper;
import org.kingsski.data.model.Race;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcRaceDao extends AbstractJdbcDao implements RaceDao {

    public static final String SELECT_ALL = "jdbcRaceSelectAll";
    public static final String SELECT_BY_LEAGUE = "jdbcRaceSelectLeague";
    public static final String SELECT_BY_LEAGUE_ROUND = "jdbcRaceSelectLeagueRound";
    public static final String SELECT_BY_SEASON_LEAGUE_ROUND = "jdbcRaceSelectSeasonLeagueRound";

    public JdbcRaceDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        super(jdbcTemplate, jdbcStatements);
    }

    @Override
    public List<Race> getRacesAll() {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_ALL), new RaceMapper());
    }

    @Override
    public List<Race> getRacesByLeague(String league) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE),
                new Object[] { league },
                new RaceMapper());
    }

    @Override
    public List<Race> getRacesByLeagueAndRound(String league, String round) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_ROUND),
                new Object[] { league, round },
                new RaceMapper());
    }

    @Override
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        // TODO historical data
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_ROUND),
                new Object[] { league, round },
                new RaceMapper());
    }
}
