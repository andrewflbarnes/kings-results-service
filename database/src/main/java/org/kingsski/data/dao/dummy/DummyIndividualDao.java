package org.kingsski.data.dao.dummy;

import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.TeamDao;
import org.kingsski.data.model.Individual;
import org.kingsski.data.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A dummy implementation of the {@link TeamDao}  interface. This class
 * has a single static list of {@link Team}s which it returns for all
 * method calls.
 *
 * @author Barnesly
 */
public class DummyIndividualDao implements IndividualDao {

    private static final List<Individual> INDIVIDUALS;
    private static final String SKUM = "SKUM";
    private static final String LEEDS = "Leeds";
    private static final String MS = "Mens ski";
    private static final String LS = "Ladies ski";
    private static final String MB = "Mens board";
    private static final String LB = "Ladies board";
    private static final String NAME = "Aidan Faria";

    // Static initialiser for the INDIVIDUALS variable
    static {
        List<Individual> dummyIndividuals = new ArrayList<>();
        dummyIndividuals.add(createIndividual(1, MS, SKUM, NAME, 10, 12, 1));
        dummyIndividuals.add(createIndividual(2, MS, LEEDS, NAME, 10, 14, 2));
        dummyIndividuals.add(createIndividual(3, MS, SKUM, NAME, 13, 12, 3));
        dummyIndividuals.add(createIndividual(1, LS, LEEDS, NAME, 10, 12, 1));
        dummyIndividuals.add(createIndividual(2, LS, LEEDS, NAME, 15, 12, 2));
        dummyIndividuals.add(createIndividual(3, LS, SKUM, NAME, 17, 12, 4));
        dummyIndividuals.add(createIndividual(1, MB, LEEDS, NAME, 10, 12, 1));
        dummyIndividuals.add(createIndividual(2, MB, SKUM, NAME, 13, 12, 2));
        dummyIndividuals.add(createIndividual(1, LB, SKUM, NAME, 10, 12, 1));
        dummyIndividuals.add(createIndividual(2, LB, LEEDS, NAME, 18, 12, 2));

        INDIVIDUALS = Collections.unmodifiableList(dummyIndividuals);
    }

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

    private static Individual createIndividual(int id, String discipline, String club, String name, int time1, int time2, int position) {
        Individual individual = new Individual();

        individual.setId(id);
        individual.setClub(club);
        individual.setDiscipline(discipline);
        individual.setName(name);
        individual.setTime1(time1);
        individual.setTime2(time2);
        individual.setTotalTime(time1 + time2);
        individual.setPosition(position);

        return individual;
    }

}
