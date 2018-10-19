package org.kingsski.query.service;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.model.Club;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubService {

    @Resource
    private ClubDao clubDao;

    public ClubService() {}

    public List<Club> getClubs() {
        return clubDao.getClubs();
    }

    public Club getClubByName(String name) {
        return clubDao.getClubByName(name);
    }

    public Club getClubById(long id) {
        return clubDao.getClubById(id);
    }
}
