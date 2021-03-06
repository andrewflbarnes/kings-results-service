-- Club Domain
CREATE TABLE t_club
  ( club_id             bigserial           NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (club_id)
);

ALTER TABLE t_club ADD CONSTRAINT UNIQUE_CLUB_NAME
UNIQUE (name);

CREATE TABLE t_team
  ( team_id             bigserial           NOT NULL
  , club_id             bigint              NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (team_id)
);

ALTER TABLE t_team ADD CONSTRAINT FK_TEAM_CLUB_CLUB_ID
FOREIGN KEY (club_id) REFERENCES t_club(club_id);

-- Organisation Domain
CREATE TABLE t_organisation
  ( organisation_id     bigserial           NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (organisation_id)
);

ALTER TABLE t_organisation ADD CONSTRAINT UNIQUE_ORGANISATION_NAME
UNIQUE (name);

CREATE TABLE t_competition
  ( competition_id      bigserial           NOT NULL
  , organisation_id     bigint              NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (competition_id)
);

ALTER TABLE t_competition ADD CONSTRAINT FK_COMPETITION_ORGANISATION_ORGANISATION_ID
FOREIGN KEY (organisation_id) REFERENCES t_organisation(organisation_id);

ALTER TABLE t_competition ADD CONSTRAINT UNIQUE_COMPETITION_ORGANISATION_ID_NAME
UNIQUE (organisation_id, name);

CREATE TABLE t_league (
  league_id           bigserial           NOT NULL,
  competition_id      bigint              NOT NULL,
  name                varchar(255)        NOT NULL,
  PRIMARY KEY         (league_id)
);

ALTER TABLE t_league ADD CONSTRAINT FK_LEAGUE_COMPETITION_COMPETITION_ID
FOREIGN KEY (competition_id) REFERENCES t_competition(competition_id);

ALTER TABLE t_league ADD CONSTRAINT UNIQUE_LEAGUE_COMPETITION_ID_NAME
UNIQUE (competition_id, name);

CREATE TABLE t_division
  ( division_id         bigserial           NOT NULL
  , competition_id      bigint              NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (division_id)
);

ALTER TABLE t_division ADD CONSTRAINT FK_DIVISION_COMPETITION_COMPETITION_ID
FOREIGN KEY (competition_id) REFERENCES t_competition(competition_id);

ALTER TABLE t_division ADD CONSTRAINT UNIQUE_DIVISION_COMPETITION_ID_NAME
UNIQUE (competition_id, name);

CREATE TABLE t_season
  ( season_id           bigserial           NOT NULL
  , competition_id      bigint              NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (season_id)
);

ALTER TABLE t_season ADD CONSTRAINT FK_SEASON_COMPETITION_COMPETITION_ID
FOREIGN KEY (competition_id) REFERENCES t_competition(competition_id);

ALTER TABLE t_season ADD CONSTRAINT UNIQUE_SEASON_COMPETITION_ID_NAME
UNIQUE (competition_id, name);

CREATE TABLE t_regional
  ( regional_id         bigserial           NOT NULL
  , season_id           bigint              NOT NULL
  , league_id           bigint              NOT NULL
  , name                varchar(255)        NOT NULL
  , PRIMARY KEY         (regional_id)
);

ALTER TABLE t_regional ADD CONSTRAINT FK_REGIONAL_LEAGUE_LEAGUE_ID
FOREIGN KEY (league_id) REFERENCES t_league(league_id);

ALTER TABLE t_regional ADD CONSTRAINT FK_REGIONAL_SEASON_SEASON_ID
FOREIGN KEY (season_id) REFERENCES t_season(season_id);

ALTER TABLE t_regional ADD CONSTRAINT UNIQUE_REGIONAL_SEASON_ID_LEAGUE_ID_NAME
UNIQUE (season_id, league_id, name);

CREATE TABLE t_score
  ( score_id            bigserial           NOT NULL
  , team_id             bigint              NOT NULL
  , regional_id         bigint              NOT NULL
  , position            bigint
  , score               bigint              NOT NULL
  , PRIMARY KEY         (score_id)
);

ALTER TABLE t_score ADD CONSTRAINT FK_SCORE_TEAM_TEAM_ID
FOREIGN KEY (team_id) REFERENCES t_team(team_id);

ALTER TABLE t_score ADD CONSTRAINT FK_SCORE_REGIONAL_REGIONAL_ID
FOREIGN KEY (regional_id) REFERENCES t_regional(regional_id);

ALTER TABLE t_score ADD CONSTRAINT UNIQUE_SCORE_TEAM_ID_REGIONAL_ID
UNIQUE (team_id, regional_id);

CREATE TABLE t_register
  ( register_id         bigserial           NOT NULL
  , team_id             bigint              NOT NULL
  , season_id           bigint              NOT NULL
  , league_id           bigint              NOT NULL
  , division_id         bigint              NOT NULL
  , PRIMARY KEY         (register_id)
);

ALTER TABLE t_register ADD CONSTRAINT FK_REGISTER_TEAM_TEAM_ID
FOREIGN KEY (team_id) REFERENCES t_team(team_id);

ALTER TABLE t_register ADD CONSTRAINT FK_REGISTER_SEASON_SEASON_ID
FOREIGN KEY (season_id) REFERENCES t_season(season_id);

ALTER TABLE t_register ADD CONSTRAINT FK_REGISTER_LEAGUE_LEAGUE_ID
FOREIGN KEY (league_id) REFERENCES t_league(league_id);

ALTER TABLE t_register ADD CONSTRAINT FK_REGISTER_DIVISION_DIVISION_ID
FOREIGN KEY (division_id) REFERENCES t_division(division_id);

ALTER TABLE t_register ADD CONSTRAINT UNIQUE_REGISTER_TEAM_ID_SEASON_ID
UNIQUE (team_id, season_id);

CREATE TABLE t_match
  ( match_id            bigserial           NOT NULL
  , regional_id         bigint              NOT NULL
  , division_id         bigint              NOT NULL
  , team_1_id           bigint              NOT NULL
  , team_2_id           bigint              NOT NULL
  , winner              int                 DEFAULT -1
  , PRIMARY KEY         (match_id)
);

ALTER TABLE t_match ADD CONSTRAINT FK_MATCH_REGIONAL_REGIONAL_ID
FOREIGN KEY (regional_id) REFERENCES t_regional(regional_id);

ALTER TABLE t_match ADD CONSTRAINT FK_MATCH_DIVISION_DIVISION_ID
FOREIGN KEY (division_id) REFERENCES t_division(division_id);

ALTER TABLE t_match ADD CONSTRAINT FK_MATCH_REGISTER_TEAM_1_ID
FOREIGN KEY (team_1_id) REFERENCES t_team(team_id);

ALTER TABLE t_match ADD CONSTRAINT FK_MATCH_REGISTER_TEAM_2_ID
FOREIGN KEY (team_2_id) REFERENCES t_team(team_id);

CREATE TABLE t_match_meta
  ( match_meta_id       bigserial           NOT NULL
  , match_id            bigint              NOT NULL
  , meta_key            varchar(255)        NOT NULL
  , meta_value          VARCHAR(255)
  , PRIMARY KEY         (match_meta_id)
);

ALTER TABLE t_match_meta ADD CONSTRAINT FK_MATCH_META_MATCH_MATCH_ID
FOREIGN KEY (match_id) REFERENCES t_match(match_id);

ALTER TABLE t_match_meta ADD CONSTRAINT UNIQUE_MATCH_META_MATCH_ID_META_KEY
UNIQUE (match_id, meta_key);
