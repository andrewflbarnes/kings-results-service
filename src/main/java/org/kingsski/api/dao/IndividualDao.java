package org.kingsski.api.dao;

import org.kingsski.api.model.Individual;

import java.util.List;

/**
 * This interface defines the API for a dao which provided
 * {@link Individual}s
 */
public interface IndividualDao {

    /**
     * Get all individuals
     *
     * @return a {@link List} of {@link Individual}s
     */
    List<Individual> getAllIndividuals();

    /**
     * Get Individuals for specific discipline
     *
     * @param discipline the discipline to get Individuals for
     * @return a {@link List} of {@link Individual}s
     */
    List<Individual> getIndividualsByDiscipline(String discipline);

    /**
     * Get Individuals for specific club
     *
     * @param club The club to get Individuals for
     * @return a {@link List} of {@link Individual}s
     */
    List<Individual> getIndividualsByClub(String club);

}
