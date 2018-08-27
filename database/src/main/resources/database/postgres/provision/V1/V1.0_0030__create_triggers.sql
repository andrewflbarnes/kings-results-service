-- Ensure that teams are registered to the league and division
CREATE OR REPLACE FUNCTION check_registered() RETURNS trigger AS $$
DECLARE
    register_id bigint;
BEGIN
    register_id := (
        SELECT r.register_id
          FROM t_register r
             , t_regional rl
             , t_league l
         WHERE NEW.regional_id = rl.regional_id
           AND rl.league_id = l.league_id
           AND NEW.team_1_id = r.team_id
           AND NEW.division_id = r.division_id
           AND l.league_id = r.league_id
           AND rl.season_id = r.season_id);

    IF register_id IS NULL OR register_id = 0 THEN
        RAISE EXCEPTION 'ERROR trg_match_check_registered: team 1 is not registered to season, league and division';
    END IF;

    register_id := (
        SELECT r.register_id
          FROM t_register r
             , t_regional rl
             , t_league l
         WHERE NEW.regional_id = rl.regional_id
           AND rl.league_id = l.league_id
           AND NEW.team_2_id = r.team_id
           AND NEW.division_id = r.division_id
           AND l.league_id = r.league_id
           AND rl.season_id = r.season_id);

    IF register_id IS NULL OR register_id = 0 THEN
        RAISE EXCEPTION 'ERROR trg_match_check_registered: team 2 is not registered to season, league and division';
    END IF;

    RETURN NEW;
END;

$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_match_check_registered BEFORE INSERT ON t_match
    FOR EACH ROW EXECUTE PROCEDURE check_registered();