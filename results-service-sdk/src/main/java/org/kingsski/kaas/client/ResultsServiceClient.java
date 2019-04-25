package org.kingsski.kaas.client;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.team.Team;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ResultsServiceClient {

    /* Club */

    @GET("/club")
    Call<List<Club>> getAllClubs();

    @GET("/club/{id}")
    Call<Club> getClubById(@Path("id") int id);

    @GET("/club/{name}")
    Call<Club> getClubByName(@Path("name") String name);

    /* Team */

    @GET("/team")
    Call<List<Team>> getAllTeams();

    @GET("/team/{id}")
    Call<Team> getTeamById(@Path("id") int id);

    @GET("/team/{name}")
    Call<Team> getTeamByName(@Path("name") String name);

    /* Organisation */

    @GET("/organisation")
    Call<List<Organisation>> getAllOrganisations();

    @GET("/organisation/{id}")
    Call<Organisation> getOrganisationById(@Path("id") int id);

    @GET("/organisation/{name}")
    Call<Organisation> getOrganisationByName(@Path("name") String name);

    /* Competition */

    @GET("/competition")
    Call<List<Competition>> getAllCompetitions();

    @GET("/competition/{id}")
    Call<Competition> getCompetitionById(@Path("id") int id);

    @GET("/competition/{name}")
    Call<Competition> getCompetitionByName(@Path("name") String name);
}
