-- Club Domain
CREATE OR REPLACE VIEW club
  ( club_id
  , name
  , teams
) AS
SELECT t_club.club_id
     , t_club.name
     , COUNT(t_team.team_id)
  FROM t_club
LEFT OUTER JOIN t_team      USING(club_id)
 GROUP
    BY t_club.club_id
     , t_club.name
 ORDER
    BY t_club.name ASC
;

CREATE OR REPLACE VIEW team
  ( team_id
  , name
  , club
) AS
SELECT t_team.team_id
     , t_team.name
     , t_club.name
  FROM t_club
  JOIN t_team               USING (club_id)
 ORDER
    BY t_club.name ASC
     , t_team.name ASC
;

-- Organisation Domain
CREATE OR REPLACE VIEW organisation
  ( organisation_id
  , name
  , competitions
) AS
SELECT t_organisation.organisation_id
     , t_organisation.name
     , COUNT(t_competition.competition_id)
  FROM t_organisation
LEFT OUTER JOIN t_competition USING (organisation_id)
 GROUP
    BY t_organisation.organisation_id
     , t_organisation.name
 ORDER
    BY t_organisation.name ASC
;

CREATE OR REPLACE VIEW competition
  ( competition_id
  , name
  , organisation
) AS
SELECT t_competition.competition_id
     , t_competition.name
     , t_organisation.name
  FROM t_competition
  JOIN t_organisation       USING (organisation_id)
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
;

CREATE OR REPLACE VIEW league
  ( league_id
  , league
  , competition
  , organisation
) AS
SELECT t_league.league_id
     , t_league.name
     , t_competition.name
     , t_organisation.name
  FROM t_league
  JOIN t_competition        USING (competition_id)
  JOIN t_organisation       USING (organisation_id)
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_league.name ASC
;

CREATE OR REPLACE VIEW division
  ( division_id
  , division
  , competition
  , organisation
) AS
SELECT t_division.division_id
     , t_division.name
     , t_competition.name
     , t_organisation.name
  FROM t_division
  JOIN t_competition        USING (competition_id)
  JOIN t_organisation       USING (organisation_id)
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_division.name ASC
;

CREATE OR REPLACE VIEW season
  ( season_id
  , season
  , competition
  , organisation
) AS
SELECT t_season.season_id
     , t_season.name
     , t_competition.name
     , t_organisation.name
  FROM t_season
  JOIN t_competition        USING (competition_id)
  JOIN t_organisation       USING (organisation_id)
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_season.name ASC
;

CREATE OR REPLACE VIEW regional
  ( regional_id
  , regional
  , league
  , season
  , competition
  , organisation
) AS
SELECT t_regional.regional_id
     , t_regional.name
     , t_league.name
     , t_season.name
     , t_competition.name
     , t_organisation.name
  FROM t_regional
  JOIN t_season             USING (season_id)
  JOIN t_competition        USING (competition_id)
  JOIN t_league             USING (competition_id, league_id)
  JOIN t_organisation       USING (organisation_id)
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_season.name ASC
     , t_league.name
     , t_regional.name
;

CREATE OR REPLACE VIEW score_regional
  ( club
  , team
  , organisation
  , competition
  , season
  , league
  , regional
  , division
  , position
  , score
) AS
SELECT t_club.name
     , t_team.name
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
     , t_division.name
     , t_score.position
     , COALESCE(t_score.score, 0) score
  FROM t_organisation
  JOIN t_competition        USING (organisation_id)
  JOIN t_season             USING (competition_id)
  JOIN t_league             USING (competition_id)
  JOIN t_division           USING (competition_id)
  JOIN t_register           USING (league_id, division_id, season_id)
  JOIN t_team               USING (team_id)
  JOIN t_club               USING (club_id)
  JOIN t_regional           USING (league_id, season_id)
  LEFT OUTER JOIN t_score   USING (team_id, regional_id)
 ORDER
    BY t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
     , t_division.name
     , score DESC
;

CREATE OR REPLACE VIEW score_competition
  ( organisation
  , competition
  , season
  , league
  , division
  , club
  , team
  , score
) AS
SELECT t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
     , t_club.name
     , t_team.name
     , SUM(COALESCE(t_score.score, 0)) score
  FROM t_organisation
  JOIN t_competition        USING (organisation_id)
  JOIN t_season             USING (competition_id)
  JOIN t_league             USING (competition_id)
  JOIN t_division           USING (competition_id)
  JOIN t_register           USING (league_id, division_id, season_id)
  JOIN t_team               USING (team_id)
  JOIN t_club               USING (club_id)
  JOIN t_regional           USING (league_id, season_id)
  LEFT OUTER JOIN t_score   USING (team_id, regional_id)
 GROUP
    BY t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_club.name
     , t_team.name
     , t_division.name
 ORDER
    BY t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
     , score DESC
;

CREATE OR REPLACE VIEW register
  ( register_id
  , organisation
  , competition
  , season
  , league
  , division
  , club
  , team
) AS
SELECT t_register.register_id
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
     , t_club.name
     , t_team.name
  FROM t_organisation
  JOIN t_competition        USING (organisation_id)
  JOIN t_season             USING (competition_id)
  JOIN t_league             USING (competition_id)
  JOIN t_division           USING (competition_id)
  JOIN t_register           USING (league_id, division_id, season_id)
  JOIN t_team               USING (team_id)
  JOIN t_club               USING (club_id)
 ORDER
    BY t_club.name
     , t_team.name
     , t_organisation.name
     , t_competition.name
     , t_league.name
     , t_division.name
;

CREATE OR REPLACE VIEW match
  ( match_id
  , competition
  , season
  , league
  , regional
  , division
  , team_1
  , team_2
  , winner
) AS
SELECT t_match.match_id
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
     , t_division.name
     , team1.name
     , team2.name
     , CASE t_match.winner
		 WHEN 0 then 'draw'
		 WHEN 1 then team1.name
		 WHEN 2 then team2.name
		 ELSE NULL
	   END
  FROM t_match
  JOIN t_team team1         ON (t_match.team_1_id = team1.team_id)
  JOIN t_team team2         ON (t_match.team_2_id = team2.team_id)
  JOIN t_regional           USING (regional_id)
  JOIN t_division           USING (division_id)
  JOIN t_competition        USING (competition_id)
  JOIN t_season             USING (competition_id, season_id)
  JOIN t_league             USING (competition_id, league_id)
 ORDER
    BY t_match.match_id
;

CREATE OR REPLACE VIEW match_meta
  ( match_meta_id
  , match_id
  , competition
  , season
  , league
  , regional
  , division
  , team_1
  , team_2
  , meta_key
  , meta_value
) AS
SELECT t_match_meta.match_meta_id
     , t_match.match_id
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
     , t_division.name
     , team1.name
     , team2.name
     , t_match_meta.meta_key
     , t_match_meta.meta_value
  FROM t_match_meta
  LEFT OUTER JOIN t_match   USING (match_id)
  JOIN t_team team1         ON (t_match.team_1_id = team1.team_id)
  JOIN t_team team2         ON (t_match.team_2_id = team2.team_id)
  JOIN t_regional           USING (regional_id)
  JOIN t_division           USING (division_id)
  JOIN t_competition        USING (competition_id)
  JOIN t_season             USING (competition_id, season_id)
  JOIN t_league             USING (competition_id, league_id)
 ORDER
    BY t_match.match_id
;
