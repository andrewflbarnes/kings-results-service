DROP TRIGGER IF EXISTS trg_match_check_registered;

DELIMITER //

-- Ensure that teams are registered to the league and division
CREATE TRIGGER trg_match_check_registered BEFORE INSERT ON `t_match`
FOR EACH ROW BEGIN
    DECLARE register_id INT;
    DECLARE msg         VARCHAR(255);

    SET register_id = (
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

    IF register_id IS NULL || register_id = 0 THEN
        SET msg = 'ERROR trg_match_check_registered: team 1 is not registered to season, league and division';
        SIGNAL SQLSTATE '45000' SET message_text = msg;
    END IF;

    SET register_id = (
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

    IF register_id IS NULL || register_id = 0 THEN
        SET msg = 'ERROR trg_match_check_registered: team 2 is not registered to season, league and division';
        SIGNAL SQLSTATE '45000' SET message_text = msg;
    END IF;
END//

DELIMITER ;