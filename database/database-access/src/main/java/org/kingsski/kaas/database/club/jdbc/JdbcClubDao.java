package org.kingsski.kaas.database.club.jdbc;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.exception.EntityAlreadyExistsException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * JDBC implementation of {@link ClubDao}
 */
public class JdbcClubDao implements ClubDao {

    private static final String SELECT_ALL = "SELECT * FROM club";
    private static final String SELECT_BY_ID = "SELECT * FROM club WHERE club_id = :id";
    private static final String SELECT_BY_NAME = "SELECT * FROM club WHERE name = :name";
    private static final String ADD_CLUB = "INSERT INTO t_club(name) VALUES(:name)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcClubDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Club> getClubs() {
        return jdbcTemplate.query(SELECT_ALL, new ClubMapper());
    }

    @Override
    public Club getClubById(long id) {
        try {
            Club club = Club.builder().id(id).build();
            return jdbcTemplate.queryForObject(
                    SELECT_BY_ID,
                    new BeanPropertySqlParameterSource(club),
                    new ClubMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Club getClubByName(String name) {
        try {
            Club club = Club.builder().name(name).build();
            return jdbcTemplate.queryForObject(
                    SELECT_BY_NAME,
                    new BeanPropertySqlParameterSource(club),
                    new ClubMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Club addClub(String name) {
        Club club = Club.builder().name(name).build();

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(club);
        KeyHolder keyholder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(ADD_CLUB, params, keyholder);
        } catch (DuplicateKeyException e) {
            throw new EntityAlreadyExistsException("club", "name", name, e);
        }

        club.setId((long)keyholder.getKeys().get("club_id"));

        return club;
    }
}
