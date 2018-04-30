package org.kingsski.api.service;

import org.kingsski.api.dao.individual.IndividualDao;
import org.kingsski.api.model.Individual;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * This class defines the service providing {@link Individual}s
 */
public class IndividualService {

    private IndividualDao individualDao;

    @Required
    public void setIndividualDao(IndividualDao individualDao) {
        this.individualDao = individualDao;
    }
    
    /**
     * Get all individuals
     * 
     * @return a {@link List} of {@link Individual}s
     */
    public List<Individual> getIndividualsAll() {
        return individualDao.getAllIndividuals();
    }
    
    /**
     * Get all individuals for a discipline
     * 
     * @param discipline the discipline to get individuals for
     * @return a {@link List} of {@link Individual}s
     */
    public List<Individual> getIndividualsByDiscipline(String discipline) {
        return individualDao.getIndividualsByDiscipline(discipline);
    }
    
    /**
     * Get all individuals for a club
     * 
     * @param club The club to get individuals for
     * @return a {@link List} of {@link Individual}s
     */
    public List<Individual> getIndividualsByClub(String club) {
        return individualDao.getIndividualsByClub(club);
    }
}
