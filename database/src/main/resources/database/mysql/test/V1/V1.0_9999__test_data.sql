INSERT INTO t_club
  ( name )
VALUES
  ( 'SKUM' );

INSERT INTO t_club
  ( name )
VALUES
  ( 'Leeds' );

INSERT INTO t_club
  ( name )
VALUES
  ( 'Bath' );

INSERT INTO t_club
  ( name )
VALUES
  ( 'No teams test' );

INSERT INTO t_team
  ( club_id
  , name
  )
VALUES
  ( (SELECT club_id FROM t_club WHERE name = 'SKUM')
  , 'SKUM 1'
  );

INSERT INTO t_team
  ( club_id
  , name
  )
VALUES
  ( (SELECT club_id FROM t_club WHERE name = 'SKUM')
  , 'SKUM 2'
  );

INSERT INTO t_team
  ( club_id
  , name
  )
VALUES
  ( (SELECT club_id FROM t_club WHERE name = 'Leeds')
  , 'Leeds 1'
  );

INSERT INTO t_team
  ( club_id
  , name
  )
VALUES
  ( (SELECT club_id FROM t_club WHERE name = 'Bath')
  , 'Bath 1'
  );

INSERT INTO t_organisation
  ( name )
VALUES
  ( 'Kings' );

INSERT INTO t_organisation
  ( name )
VALUES
  ( 'Test empty' );

INSERT INTO t_competition
  ( organisation_id
  , name
  )
VALUES
  ( (SELECT organisation_id FROM t_organisation WHERE name = 'Kings')
  , 'Kings Ski'
  );

INSERT INTO t_league
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Northern'
  )
;

INSERT INTO t_league
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Midlands'
  )
;

INSERT INTO t_league
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Southern'
  )
;

INSERT INTO t_league
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Western'
  )
;

INSERT INTO t_division
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Mixed'
  )
;

INSERT INTO t_division
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Ladies'
  )
;

INSERT INTO t_division
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Board'
  )
;

INSERT INTO t_season
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , '2017'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 1'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 2'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 3'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 4'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 1'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 2'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 3'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 4'
  )
;

INSERT INTO t_register
  ( team_id
  , season_id
  , league_id
  , division_id
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
;

INSERT INTO t_register
  ( team_id
  , season_id
  , league_id
  , division_id
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'SKUM 2')
  , (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
;

INSERT INTO t_register
  ( team_id
  , season_id
  , league_id
  , division_id
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
;

INSERT INTO t_register
  ( team_id
  , season_id
  , league_id
  , division_id
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT season_id FROM t_season WHERE name = '2017')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , 20
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , 18
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 2'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , 20
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Western')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , 20
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 2'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Western')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , 20
  )
;

INSERT INTO t_match
  ( regional_id
  , division_id
  , team_1_id
  , team_2_id
  , winner
  )
VALUES
  ( (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , 1
  )
;

INSERT INTO t_match
  ( regional_id
  , division_id
  , team_1_id
  , team_2_id
  )
VALUES
  ( (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2017'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  )
;

COMMIT;