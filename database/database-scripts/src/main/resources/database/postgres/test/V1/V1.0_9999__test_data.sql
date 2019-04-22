INSERT INTO t_club
  ( name )
VALUES
  ( 'SKUM' )
, ( 'Leeds' )
, ( 'Bath' )
, ( 'No teams test' )
;

INSERT INTO t_team
  ( club_id
  , name
  )
VALUES
  ( (SELECT club_id FROM t_club WHERE name = 'SKUM')
  , 'SKUM 1'
  )
, ( (SELECT club_id FROM t_club WHERE name = 'SKUM')
  , 'SKUM 2'
  )
, ( (SELECT club_id FROM t_club WHERE name = 'Leeds')
  , 'Leeds 1'
  )
, ( (SELECT club_id FROM t_club WHERE name = 'Bath')
  , 'Bath 1'
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
  , (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
, ( (SELECT team_id FROM t_team WHERE name = 'SKUM 2')
  , (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
, ( (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
, ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  )
;

INSERT INTO t_score
  ( team_id
  , regional_id
  , position
  , score
  )
VALUES
  ( (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , 1
  , 20
  )
, ( (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , 2
  , 18
  )
, ( (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 2'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , 1
  , 20
  )
, ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Western')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , 1
  , 20
  )
, ( (SELECT team_id FROM t_team WHERE name = 'Bath 1')
  , (SELECT regional_id FROM t_regional WHERE name = 'Round 2'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Western')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , 1
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
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , -1
  )
, ( (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , 0
  )
, ( (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , 1
  )
, ( (SELECT regional_id FROM t_regional WHERE name = 'Round 1'
                                          AND league_id = (SELECT league_id FROM t_league WHERE name = 'Northern')
                                          AND season_id = (SELECT season_id FROM t_season WHERE name = '2018/19'))
  , (SELECT division_id FROM t_division WHERE name = 'Mixed')
  , (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
  , (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
  , 2
  )
;

INSERT INTO t_match_meta
  ( match_id
  , meta_key
  , meta_value
  )
VALUES
  ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1'))
  , 'TYPE'
  , 'Kings'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1'))
  , 'SET'
  , '1'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1'))
  , 'MINI_LEAGUE'
  , 'A'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1'))
  , 'RACE_NO'
  , '1'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1'))
  , 'TYPE'
  , 'Kings'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1'))
  , 'SET'
  , '1'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1'))
  , 'MINI_LEAGUE'
  , 'A'
  )
, ( (SELECT MAX(match_id) FROM t_match WHERE team_1_id = (SELECT team_id FROM t_team WHERE name = 'Leeds 1')
                                         AND team_2_id = (SELECT team_id FROM t_team WHERE name = 'SKUM 1'))
  , 'RACE_NO'
  , '2'
  )
;
COMMIT;
