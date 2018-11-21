-- Views for use with the KAAS data models
CREATE OR REPLACE VIEW kaas_register
  ( team_id
  , team
  , club
  , register_id
  , organisation
  , competition
  , season
  , league
  , division
) AS
SELECT t_team.team_id
     , t_team.name
     , t_club.name
     , t_register.register_id
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
  FROM t_team
  JOIN t_club               USING (club_id)
  JOIN t_register           USING (team_id)
  JOIN t_season             USING (season_id)
  JOIN t_competition        USING (competition_id)
  JOIN t_organisation       USING (organisation_id)
  JOIN t_league             USING (league_id)
  JOIN t_division           USING (division_id)
 ORDER
    BY t_team.team_id
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
;

-- Views for use with the KAAS data models
CREATE OR REPLACE VIEW kaas_team
  ( team_id
  , team
  , club
  , organisation
  , competition
  , season
  , league
  , division
  , regional
  , score
) AS
SELECT t_team.team_id
     , t_team.name
     , t_club.name
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
     , t_regional.name
     , COALESCE(t_score.score, 0) score
  FROM t_team
  JOIN t_club               USING (club_id)
  JOIN t_register           USING (team_id)
  JOIN t_season             USING (season_id)
  JOIN t_competition        USING (competition_id)
  JOIN t_organisation       USING (organisation_id)
  JOIN t_league             USING (league_id)
  JOIN t_division           USING (division_id)
  JOIN t_regional           USING (season_id, league_id)
  LEFT OUTER JOIN t_score   USING (team_id, regional_id)
 ORDER
    BY t_team.team_id
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
     , t_regional.name
     , score DESC
;
