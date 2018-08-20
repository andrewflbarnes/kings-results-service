CREATE TABLE `kings_teams` (
  `id`          bigint(20)      NOT NULL AUTO_INCREMENT,
  `teamName`        varchar(255)    NOT NULL,
  `division`    varchar(255)    NOT NULL,
  `league`      varchar(255)    NOT NULL,
  `position`    int(11)         DEFAULT 0,
  `r1`          int(11)         DEFAULT 0,
  `r2`          int(11)         DEFAULT 0,
  `r3`          int(11)         DEFAULT 0,
  `r4`          int(11)         DEFAULT 0,
  `total`       int(11)         DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_ldp` (`league`,`division`,`position`));

CREATE TABLE `kings_races` (
  `id`              bigint(20)      NOT NULL AUTO_INCREMENT,
  `league`          varchar(255)    NOT NULL,
  `round`           varchar(255)    NOT NULL,
  `set`             varchar(255)    NOT NULL,
  `race_no`         int(11)         NOT NULL,
  `division`        varchar(255)    NOT NULL,
  `team_one`        varchar(255)    NOT NULL,
  `team_two`        varchar(255)    NOT NULL,
  `winner`          varchar(255)    DEFAULT NULL,
  `team_one_dsq`    varchar(255)    DEFAULT NULL,
  `team_two_dsq`    varchar(255)    DEFAULT NULL,
  `next`            int(1)          DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `race_lrsr` (`league`,`round`,`set`, `race_no`));

CREATE TABLE `kings_individuals` (
  `id`              bigint(20)      NOT NULL AUTO_INCREMENT,
  `name`            varchar(255)    NOT NULL,
  `discipline`      varchar(255)    NOT NULL,
  `club`            varchar(255)    NOT NULL,
  `position`        int(11)         DEFAULT 0,
  `time_one`        int(11)         DEFAULT 0,
  `time_two`        int(11)         DEFAULT 0,
  `total_time`      int(11)         DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indi_ldc` (`name`,`discipline`,`club`));