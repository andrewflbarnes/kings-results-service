-- Club Domain
CREATE OR REPLACE VIEW club (
  name,
  teams
) AS
SELECT t_club.name
     , COUNT(t_team.team_id)
  FROM t_club
LEFT OUTER JOIN t_team
    ON t_club.club_id = t_team.club_id
 GROUP
    BY t_club.name
 ORDER
    BY t_club.name ASC
;

CREATE OR REPLACE VIEW team (
  club,
  name
) AS
SELECT t_club.name
     , t_team.name
  FROM t_team
     , t_club
 WHERE t_team.club_id = t_club.club_id
 ORDER
    BY t_club.name ASC
     , t_team.name ASC
;

-- Organisation Domain
CREATE OR REPLACE VIEW organisation (
  name,
  competitions
) AS
SELECT t_organisation.name
     , COUNT(t_competition.competition_id)
  FROM t_organisation
LEFT OUTER JOIN t_competition
    ON t_organisation.organisation_id = t_competition.organisation_id
 GROUP
    BY t_organisation.name
 ORDER
    BY t_organisation.name ASC
;

CREATE OR REPLACE VIEW competition (
  organisation,
  name
) AS
SELECT t_organisation.name
     , t_competition.name
  FROM t_competition
     , t_organisation
 WHERE t_competition.organisation_id = t_organisation.organisation_id
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
;

CREATE OR REPLACE VIEW league (
  organisation,
  competition,
  league
) AS
SELECT t_organisation.name
     , t_competition.name
     , t_league.name
  FROM t_league
     , t_organisation
     , t_competition
 WHERE t_league.competition_id = t_competition.competition_id
   AND t_competition.organisation_id = t_organisation.organisation_id
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_league.name ASC
;

CREATE OR REPLACE VIEW division (
  organisation,
  competition,
  division
) AS
SELECT t_organisation.name
     , t_competition.name
     , t_division.name
  FROM t_division
     , t_organisation
     , t_competition
 WHERE t_division.competition_id = t_competition.competition_id
   AND t_competition.organisation_id = t_organisation.organisation_id
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_division.name ASC
;

CREATE OR REPLACE VIEW season (
  organisation,
  competition,
  season
) AS
SELECT t_organisation.name
     , t_competition.name
     , t_season.name
  FROM t_season
     , t_organisation
     , t_competition
 WHERE t_season.competition_id = t_competition.competition_id
   AND t_competition.organisation_id = t_organisation.organisation_id
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_season.name ASC
;

CREATE OR REPLACE VIEW regional (
  organisation,
  competition,
  season,
  league,
  regional
) AS
SELECT t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
  FROM t_regional
     , t_season
     , t_league
     , t_organisation
     , t_competition
 WHERE t_regional.league_id = t_league.league_id
   AND t_league.competition_id = t_competition.competition_id
   AND t_competition.organisation_id = t_organisation.organisation_id
   AND t_regional.season_id = t_season.season_id
 ORDER
    BY t_organisation.name ASC
     , t_competition.name ASC
     , t_season.name ASC
     , t_league.name ASC
     , t_regional.name ASC
;

CREATE OR REPLACE VIEW register
  ( club
  , team
  , organisation
  , competition
  , season
  , league
  , division
) AS
SELECT t_club.name
     , t_team.name
     , t_organisation.name
     , t_competition.name
     , t_season.name
     , t_league.name
     , t_division.name
  FROM t_register
     , t_season
     , t_league
     , t_division
     , t_organisation
     , t_competition
     , t_club
     , t_team
 WHERE t_register.league_id = t_league.league_id
   AND t_register.division_id = t_division.division_id
   AND t_register.season_id = t_season.season_id
   AND t_league.competition_id = t_competition.competition_id
   AND t_competition.organisation_id = t_organisation.organisation_id
   AND t_register.team_id = t_team.team_id
   AND t_team.club_id = t_club.club_id
 ORDER
    BY t_club.name
     , t_team.name
     , t_organisation.name
     , t_competition.name
     , t_league.name
     , t_division.name
;

CREATE OR REPLACE VIEW `match` (
  competition,
  season,
  league,
  regional,
  division,
  team_1,
  team_2,
  winner
) AS
SELECT t_competition.name
     , t_season.name
     , t_league.name
     , t_regional.name
     , t_division.name
     , team1.name
     , team2.name
     , CASE t_match.winner
		 WHEN 1 then team1.name
		 WHEN 2 then team2.name
		 ELSE NULL
	   END AS 'winner'
  FROM t_match
     , t_competition
     , t_season
     , t_league
     , t_division
     , t_regional
     , t_team AS team1
     , t_team AS team2
 WHERE t_match.regional_id = t_regional.regional_id
   AND t_regional.league_id = t_league.league_id
   AND t_regional.season_id = t_season.season_id
   AND t_match.team_1_id = team1.team_id
   AND t_match.team_2_id = team2.team_id
   AND t_match.division_id = t_division.division_id
 ORDER
    BY t_match.match_id
;
