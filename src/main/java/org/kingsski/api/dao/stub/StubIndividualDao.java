package org.kingsski.api.dao.stub;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.model.Individual;

import java.util.Collections;
import java.util.List;

public class StubIndividualDao implements IndividualDao {

    private static final List<Individual> INDIVIDUALS = Collections.emptyList();

    @Override
    public List<Individual> getAllIndividuals() {
        return INDIVIDUALS;
    }

    @Override
    public List<Individual> getIndividualsByDiscipline(String discipline) {
        return INDIVIDUALS;
    }

    @Override
    public List<Individual> getIndividualsByClub(String club) {
        return INDIVIDUALS;
    }
}
