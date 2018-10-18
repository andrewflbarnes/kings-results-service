package org.kingsski.service;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.model.Club;

import java.util.List;

public class ClubService {

    private ClubDao clubDao;

    public ClubService() {}

    public List<Club> getClubs() {
        return clubDao.getClubs();
    }

    public void setClubDao(ClubDao clubDao) {
        this.clubDao = clubDao;
    }
}
