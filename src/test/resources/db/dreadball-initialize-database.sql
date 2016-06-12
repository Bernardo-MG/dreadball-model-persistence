--
--  Copyright 2015 the original author or authors
--
--  Licensed under the Apache License, Version 2.0 (the "License"); you may not
--  use this file except in compliance with the License. You may obtain a copy of
--  the License at
--
--  http://www.apache.org/licenses/LICENSE-2.0
--
--  Unless required by applicable law or agreed to in writing, software
--  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
--  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
--  License for the specific language governing permissions and limitations under
--  the License.
--


-- ****************************************
-- This SQL script creates the tables to hold the data for the Dreadball entities.
-- ****************************************


-- ****************************************
--                DROPS
-- ****************************************


-- Aggregation tables

DROP TABLE IF EXISTS unit_abilities;

DROP TABLE IF EXISTS unit_affinities;
DROP TABLE IF EXISTS unit_hated_affinities;

DROP TABLE IF EXISTS team_type_rules;

DROP TABLE IF EXISTS sponsor_affinity_groups;

DROP TABLE IF EXISTS component_abilities;

DROP TABLE IF EXISTS composite_unit_components;

DROP TABLE IF EXISTS sponsor_affinity_avas_affinity_groups;


-- Availabilities tables

DROP TABLE IF EXISTS team_type_unit_avas;
DROP TABLE IF EXISTS team_type_mvp_avas;

DROP TABLE IF EXISTS team_type_asset_avas;

DROP TABLE IF EXISTS sponsor_affinity_avas;

DROP TABLE IF EXISTS sponsor_asset_avas;


-- Team tables

DROP TABLE IF EXISTS team_types;
DROP TABLE IF EXISTS team_rules;

DROP TABLE IF EXISTS sponsors;


-- Unit tables

DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS affinity_units;
DROP TABLE IF EXISTS composite_affinity_units;
DROP TABLE IF EXISTS advancement_units;
DROP TABLE IF EXISTS composite_advancement_units;

DROP TABLE IF EXISTS component_positions;
DROP TABLE IF EXISTS affinity_unit_components;
DROP TABLE IF EXISTS unit_components;
DROP TABLE IF EXISTS component_locations;

DROP TABLE IF EXISTS affinity_groups;

DROP TABLE IF EXISTS abilities;


-- ****************************************
--                CREATION
-- ****************************************


-- Unit tables

CREATE TABLE abilities (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE affinity_groups (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE component_locations (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE unit_components (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE,
    location_id             INTEGER NOT NULL DEFAULT 0,
    cost                    INTEGER NOT NULL DEFAULT 0,
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (location_id) REFERENCES component_locations (id) ON DELETE CASCADE
);

CREATE TABLE affinity_unit_components (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE,
    location_id             INTEGER NOT NULL DEFAULT 0,
    cost                    INTEGER NOT NULL DEFAULT 0,
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    cost_ally               INTEGER NOT NULL DEFAULT 0,
    cost_friend             INTEGER NOT NULL DEFAULT 0,
    cost_stranger           INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (location_id) REFERENCES component_locations (id) ON DELETE CASCADE
);

CREATE TABLE component_positions (
    component_id            INTEGER,
      position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    FOREIGN KEY (component_id) REFERENCES unit_components (id) ON DELETE CASCADE
);

CREATE TABLE composite_advancement_units (
    id                      INTEGER PRIMARY KEY,
    template_name           VARCHAR(30) NOT NULL DEFAULT '',
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    cost                    INTEGER NOT NULL DEFAULT 0,
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    giant                   BOOLEAN NOT NULL DEFAULT FALSE,
    experience              INTEGER NOT NULL DEFAULT 0,
    rank                    INTEGER NOT NULL DEFAULT 0,
    grafted_implant_id      INTEGER,
    FOREIGN KEY (grafted_implant_id) REFERENCES unit_components (id) ON DELETE CASCADE
);

CREATE TABLE advancement_units (
    id                      INTEGER PRIMARY KEY,
    template_name           VARCHAR(30) NOT NULL DEFAULT '',
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    cost                    INTEGER NOT NULL DEFAULT 0,
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    giant                   BOOLEAN NOT NULL DEFAULT FALSE,
    experience              INTEGER NOT NULL DEFAULT 0,
    rank                    INTEGER NOT NULL DEFAULT 0,
    grafted_implant_id      INTEGER,
    FOREIGN KEY (grafted_implant_id) REFERENCES unit_components (id) ON DELETE CASCADE
);

CREATE TABLE composite_affinity_units (
    id                      INTEGER PRIMARY KEY,
    template_name           VARCHAR(30) NOT NULL UNIQUE,
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    giant                   BOOLEAN NOT NULL DEFAULT FALSE,
    cost_ally               INTEGER NOT NULL DEFAULT 0,
    cost_friend             INTEGER NOT NULL DEFAULT 0,
    cost_stranger           INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE affinity_units (
    id                      INTEGER PRIMARY KEY,
    template_name           VARCHAR(30) NOT NULL UNIQUE,
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    giant                   BOOLEAN NOT NULL DEFAULT FALSE,
    cost_ally               INTEGER NOT NULL DEFAULT 0,
    cost_friend             INTEGER NOT NULL DEFAULT 0,
    cost_stranger           INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE units (
    id                      INTEGER PRIMARY KEY,
    template_name           VARCHAR(30) NOT NULL UNIQUE,
    cost                    INTEGER NOT NULL DEFAULT 0,
    armor                   INTEGER NOT NULL DEFAULT 0,
    movement                INTEGER NOT NULL DEFAULT 0,
    skill                   INTEGER NOT NULL DEFAULT 0,
    speed                   INTEGER NOT NULL DEFAULT 0,
    strength                INTEGER NOT NULL DEFAULT 0,
    position                VARCHAR(30) NOT NULL DEFAULT 'JACK',
    giant                   BOOLEAN NOT NULL DEFAULT FALSE
);


-- Team tables

CREATE TABLE sponsors (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    cash                    INTEGER NOT NULL DEFAULT 0,
    rank                    INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE team_rules (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE team_types (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL UNIQUE
);


-- Availabilities tables

CREATE TABLE sponsor_asset_avas (
    id                      INTEGER PRIMARY KEY,
    cost_affinity           INTEGER NOT NULL DEFAULT 0,
    cost_cheerleader        INTEGER NOT NULL DEFAULT 0,
    cost_cheerleader_unlock INTEGER NOT NULL DEFAULT 0,
    cost_dice               INTEGER NOT NULL DEFAULT 0,
    cost_medibot            INTEGER NOT NULL DEFAULT 0,
    cost_sabotage           INTEGER NOT NULL DEFAULT 0,
    cost_special_move       INTEGER NOT NULL DEFAULT 0,
    cost_wager              INTEGER NOT NULL DEFAULT 0,
    max_wager               INTEGER NOT NULL DEFAULT 0,
    min_team_cost           INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE sponsor_affinity_avas (
    id                      INTEGER PRIMARY KEY,
    name                    VARCHAR(30) NOT NULL DEFAULT '',
    rank_increase           BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE team_type_asset_avas (
    id                      INTEGER PRIMARY KEY,
    team_type_id            INTEGER NOT NULL DEFAULT 0,
    cost_card               INTEGER NOT NULL DEFAULT 0,
    cost_cheerleader        INTEGER NOT NULL DEFAULT 0,
    cost_coaching           INTEGER NOT NULL DEFAULT 0,
    cost_dice               INTEGER NOT NULL DEFAULT 0,
    initial_card            INTEGER NOT NULL DEFAULT 0,
    initial_cheerleader     INTEGER NOT NULL DEFAULT 0,
    initial_dice            INTEGER NOT NULL DEFAULT 0,
    max_card                INTEGER NOT NULL DEFAULT 0,
    max_cheerleader         INTEGER NOT NULL DEFAULT 0,
    max_dice                INTEGER NOT NULL DEFAULT 0,
    def_coach               BOOLEAN NOT NULL DEFAULT FALSE,
    off_coach               BOOLEAN NOT NULL DEFAULT FALSE,
    sup_coach               BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (team_type_id) REFERENCES team_types (id) ON DELETE CASCADE
);

CREATE TABLE team_type_mvp_avas (
    id                      INTEGER PRIMARY KEY,
    team_type_id            INTEGER NOT NULL DEFAULT 0,
    unit_id                 INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (team_type_id) REFERENCES team_types (id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE
);

CREATE TABLE team_type_unit_avas (
    id                      INTEGER PRIMARY KEY,
    team_type_id            INTEGER,
    unit_id                 INTEGER,
    initial                 INTEGER NOT NULL DEFAULT 0,
    max                     INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (team_type_id) REFERENCES team_types (id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE
);


-- Aggregation tables

CREATE TABLE unit_abilities (
    unit_id                 INTEGER,
    ability_id              INTEGER,
    FOREIGN KEY (ability_id) REFERENCES abilities (id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE
);

CREATE TABLE unit_affinities (
    unit_id                 INTEGER,
    affinity_id             INTEGER,
    FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES affinity_units (id) ON DELETE CASCADE
);

CREATE TABLE unit_hated_affinities (
    unit_id                 INTEGER,
    affinity_id             INTEGER,
    FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES affinity_units (id) ON DELETE CASCADE
);

CREATE TABLE team_type_rules (
    team_type_id            INTEGER,
    team_rule_id            INTEGER,
    FOREIGN KEY (team_type_id) REFERENCES team_types (id) ON DELETE CASCADE,
    FOREIGN KEY (team_rule_id) REFERENCES team_rules (id) ON DELETE CASCADE
);

CREATE TABLE sponsor_affinity_groups (
    sponsor_id              INTEGER,
    group_id                INTEGER,
    FOREIGN KEY (sponsor_id) REFERENCES sponsors (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES affinity_groups (id) ON DELETE CASCADE
);

CREATE TABLE component_abilities (
    component_id            INTEGER,
    ability_id              INTEGER,
    FOREIGN KEY (ability_id) REFERENCES abilities (id) ON DELETE CASCADE,
    FOREIGN KEY (component_id) REFERENCES unit_components (id) ON DELETE CASCADE
);

CREATE TABLE composite_unit_components (
    unit_id                 INTEGER,
    component_id            INTEGER,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE,
    FOREIGN KEY (component_id) REFERENCES unit_components (id) ON DELETE CASCADE
);

CREATE TABLE sponsor_affinity_avas_affinity_groups (
    sponsor_affinity_ava_id    INTEGER,
    affinity_id                INTEGER,
    FOREIGN KEY (sponsor_affinity_ava_id) REFERENCES sponsor_affinity_avas (id) ON DELETE CASCADE,
    FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id) ON DELETE CASCADE
);
