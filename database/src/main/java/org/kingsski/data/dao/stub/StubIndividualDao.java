package org.kingsski.data.dao.stub;

import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.jdbc.mapper.IndividualMapper;
import org.kingsski.data.model.Individual;

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
