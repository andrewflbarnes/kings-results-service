package org.kingsski.database.club.jdbc;

import org.kingsski.database.club.Club;
import org.kingsski.database.club.ClubDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link ClubDao}
 */
public class JdbcClubDao implements ClubDao {

    private static final String SELECT_ALL = "SELECT * FROM club";
    private static final String SELECT_BY_ID = "SELECT * FROM club WHERE club_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM club WHERE name = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcClubDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Club> getClubs() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new ClubMapper());
    }

    @Override
    public Club getClubById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new ClubMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Club getClubByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new ClubMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
