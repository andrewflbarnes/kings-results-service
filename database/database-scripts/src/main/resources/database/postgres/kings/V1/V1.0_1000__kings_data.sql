INSERT INTO t_organisation
  ( name )
VALUES
  ( 'Kings' )
, ( 'Test empty' );

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
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Midlands'
  )
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Southern'
  )
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
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
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Ladies'
  )
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , 'Board'
  )
;

INSERT INTO t_season
  ( competition_id
  , name
  )
VALUES
  ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , '2018/19'
  )
, ( (SELECT competition_id FROM t_competition WHERE name = 'Kings Ski')
  , '2019/20'
  )
;

INSERT INTO t_regional
  ( season_id
  , league_id
  , name
  )
VALUES
  ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 1'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 2'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 3'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Northern')
  , 'Round 4'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 1'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 2'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 3'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Western')
  , 'Round 4'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Southern')
  , 'Round 1'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Southern')
  , 'Round 2'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Southern')
  , 'Round 3'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Southern')
  , 'Round 4'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Midlands')
  , 'Round 1'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Midlands')
  , 'Round 2'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Midlands')
  , 'Round 3'
  )
, ( (SELECT season_id FROM t_season WHERE name = '2018/19')
  , (SELECT league_id FROM t_league WHERE name = 'Midlands')
  , 'Round 4'
  )
;

COMMIT;
