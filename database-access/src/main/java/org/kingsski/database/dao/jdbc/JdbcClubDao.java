package org.kingsski.database.dao.jdbc;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.jdbc.mapper.ClubMapper;
import org.kingsski.database.model.Club;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link ClubDao}
 */
public class JdbcClubDao implements ClubDao {

    private final String SELECT_ALL = "SELECT * FROM club";
    private final String SELECT_BY_ID = "SELECT * FROM club WHERE club_id = ?";
    private final String SELECT_BY_NAME = "SELECT * FROM club WHERE name = ?";
    private final String CREATE = "INSERT INTO t_club ( name ) VALUES ( ? )";
    private final String UPDATE = "UPDATE t_club SET name = ? WHERE club_id = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcClubDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Club createClub(Club club) {
        jdbcTemplate.update(CREATE, new Object[]{club.getName()});
        return getClubByName(club.getName());
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

    @Override
    public void updateClub(Club club) {
        jdbcTemplate.update(UPDATE, club.getName(), club.getId());
    }
}
