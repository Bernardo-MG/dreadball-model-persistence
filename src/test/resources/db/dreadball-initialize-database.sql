
-- ****************************************
--                DROPS
-- ****************************************

-- Aggregation tables

DROP TABLE unit_abilities IF EXISTS;

DROP TABLE unit_affinities IF EXISTS;
DROP TABLE unit_hated_affinities IF EXISTS;

DROP TABLE team_type_rules IF EXISTS;

DROP TABLE sponsor_affinity_groups IF EXISTS;

DROP TABLE component_abilities IF EXISTS;

DROP TABLE composite_unit_components IF EXISTS;

DROP TABLE sponsor_affinity_avas_affinity_groups IF EXISTS;

-- Availabilities tables

DROP TABLE team_type_unit_avas IF EXISTS;
DROP TABLE team_type_mvp_avas IF EXISTS;

DROP TABLE team_type_asset_avas IF EXISTS;

DROP TABLE sponsor_affinity_avas IF EXISTS;

DROP TABLE sponsor_asset_avas IF EXISTS;

-- Team tables

DROP TABLE team_types IF EXISTS;
DROP TABLE team_rules IF EXISTS;

DROP TABLE sponsors IF EXISTS;

-- Unit tables

DROP TABLE units IF EXISTS;
DROP TABLE affinity_units IF EXISTS;
DROP TABLE composite_affinity_units IF EXISTS;
DROP TABLE advancement_units IF EXISTS;

DROP TABLE component_positions IF EXISTS;
DROP TABLE affinity_unit_components IF EXISTS;
DROP TABLE unit_components IF EXISTS;
DROP TABLE component_locations IF EXISTS;

DROP TABLE affinity_groups IF EXISTS;

DROP TABLE abilities IF EXISTS;


-- ****************************************
--                CREATION
-- ****************************************

-- Unit tables

CREATE TABLE abilities (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30)
);

CREATE TABLE affinity_groups (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30)
);

CREATE TABLE component_locations (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30)
);

CREATE TABLE unit_components (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	location_id			INTEGER,
	cost				INTEGER DEFAULT 0,
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0
);
ALTER TABLE unit_components ADD CONSTRAINT fk_unit_components_location FOREIGN KEY (location_id) REFERENCES component_locations (id);

CREATE TABLE affinity_unit_components (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	location_id			INTEGER,
	cost				INTEGER DEFAULT 0,
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0,
	cost_ally			INTEGER DEFAULT 0,
	cost_friend			INTEGER DEFAULT 0,
	cost_stranger		INTEGER DEFAULT 0
);
ALTER TABLE affinity_unit_components ADD CONSTRAINT fk_affinity_unit_components_location FOREIGN KEY (location_id) REFERENCES component_locations (id);

CREATE TABLE component_positions (
	component_id		INTEGER,
  	position			VARCHAR(30)
);
ALTER TABLE component_positions ADD CONSTRAINT fk_component_positions_component FOREIGN KEY (component_id) REFERENCES unit_components (id);

CREATE TABLE advancement_units (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	cost				INTEGER DEFAULT 0,
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0,
	position			VARCHAR(30),
	giant				BOOLEAN DEFAULT FALSE,
	experience			INTEGER DEFAULT 0,
	rank				INTEGER DEFAULT 0,
	grafted_implant_id 	INTEGER
);
ALTER TABLE advancement_units ADD CONSTRAINT fk_advancement_units_component FOREIGN KEY (grafted_implant_id) REFERENCES unit_components (id);

CREATE TABLE composite_affinity_units (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0,
	position			VARCHAR(30),
	giant				BOOLEAN DEFAULT FALSE,
	cost_ally			INTEGER DEFAULT 0,
	cost_friend			INTEGER DEFAULT 0,
	cost_stranger		INTEGER DEFAULT 0
);

CREATE TABLE affinity_units (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0,
	position			VARCHAR(30),
	giant				BOOLEAN DEFAULT FALSE,
	cost_ally			INTEGER DEFAULT 0,
	cost_friend			INTEGER DEFAULT 0,
	cost_stranger		INTEGER DEFAULT 0
);

CREATE TABLE units (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	cost				INTEGER DEFAULT 0,
	armor				INTEGER DEFAULT 0,
	movement			INTEGER DEFAULT 0,
	skill				INTEGER DEFAULT 0,
	speed				INTEGER DEFAULT 0,
	strength			INTEGER DEFAULT 0,
	position			VARCHAR(30),
	giant				BOOLEAN DEFAULT FALSE
);

-- Team tables

CREATE TABLE sponsors (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	cash				INTEGER DEFAULT 0,
	rank				INTEGER DEFAULT 0
);

CREATE TABLE team_rules (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30)
);

CREATE TABLE team_types (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30)
);

-- Availabilities tables


CREATE TABLE sponsor_asset_avas (
	id					INTEGER IDENTITY PRIMARY KEY,
	cost_affinity		INTEGER,
	cost_cheerleader	INTEGER,
	cost_cheerleader_unlock	INTEGER,
	cost_dice			INTEGER,
	cost_medibot		INTEGER,
	cost_sabotage		INTEGER,
	cost_special_move	INTEGER,
	cost_wager			INTEGER,
	max_wager			INTEGER,
	min_team_cost		INTEGER
);

CREATE TABLE sponsor_affinity_avas (
	id					INTEGER IDENTITY PRIMARY KEY,
	name				VARCHAR(30),
	rank_increase		BOOLEAN
);

CREATE TABLE team_type_asset_avas (
	id					INTEGER IDENTITY PRIMARY KEY,
	team_type_id		INTEGER,
	cost_card			INTEGER,
	cost_cheerleader	INTEGER,
	cost_coaching		INTEGER,
	cost_dice			INTEGER,
	initial_card		INTEGER,
	initial_cheerleader	INTEGER,
	initial_dice		INTEGER,
	max_card			INTEGER,
	max_cheerleader		INTEGER,
	max_dice			INTEGER,
	def_coach			BOOLEAN,
	off_coach			BOOLEAN,
	sup_coach			BOOLEAN,
);
ALTER TABLE team_type_asset_avas ADD CONSTRAINT fk_team_type_asset_avas_team FOREIGN KEY (team_type_id) REFERENCES team_types (id);

CREATE TABLE team_type_mvp_avas (
	id					INTEGER IDENTITY PRIMARY KEY,
	team_type_id		INTEGER,
	unit_id				INTEGER
);
ALTER TABLE team_type_mvp_avas ADD CONSTRAINT fk_team_type_mvp_avas_team FOREIGN KEY (team_type_id) REFERENCES team_types (id);
ALTER TABLE team_type_mvp_avas ADD CONSTRAINT fk_team_type_mvp_avas_unit FOREIGN KEY (unit_id) REFERENCES units (id);

CREATE TABLE team_type_unit_avas (
	id					INTEGER IDENTITY PRIMARY KEY,
	initial				INTEGER,
	max					INTEGER,
	team_type_id		INTEGER,
	unit_id				INTEGER
);
ALTER TABLE team_type_unit_avas ADD CONSTRAINT fk_team_type_unit_avas_team FOREIGN KEY (team_type_id) REFERENCES team_types (id);
ALTER TABLE team_type_unit_avas ADD CONSTRAINT fk_team_type_unit_avas_unit FOREIGN KEY (unit_id) REFERENCES units (id);

-- Aggregation tables

CREATE TABLE unit_abilities (
	unit_id				INTEGER,
  	ability_id			INTEGER
);
ALTER TABLE unit_abilities ADD CONSTRAINT fk_unit_abilities_ability FOREIGN KEY (ability_id) REFERENCES abilities (id);
ALTER TABLE unit_abilities ADD CONSTRAINT fk_unit_abilities_unit FOREIGN KEY (unit_id) REFERENCES units (id);

CREATE TABLE unit_affinities (
	unit_id				INTEGER,
  	affinity_id			INTEGER
);
ALTER TABLE unit_affinities ADD CONSTRAINT fk_unit_affinities_affinity FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id);
ALTER TABLE unit_affinities ADD CONSTRAINT fk_unit_affinities_unit FOREIGN KEY (unit_id) REFERENCES affinity_units (id);

CREATE TABLE unit_hated_affinities (
	unit_id				INTEGER,
  	affinity_id			INTEGER
);
ALTER TABLE unit_hated_affinities ADD CONSTRAINT fk_unit_hated_affinities_affinity FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id);
ALTER TABLE unit_hated_affinities ADD CONSTRAINT fk_unit_hated_affinities_unit FOREIGN KEY (unit_id) REFERENCES affinity_units (id);

CREATE TABLE team_type_rules (
	team_type_id		INTEGER,
  	team_rule_id		INTEGER
);
ALTER TABLE team_type_rules ADD CONSTRAINT fk_team_type_rules_team_type FOREIGN KEY (team_type_id) REFERENCES team_types (id);
ALTER TABLE team_type_rules ADD CONSTRAINT fk_team_type_rules_team_rule FOREIGN KEY (team_rule_id) REFERENCES team_rules (id);

CREATE TABLE sponsor_affinity_groups (
	sponsor_id			INTEGER,
  	group_id			INTEGER
);
ALTER TABLE sponsor_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_sponsor FOREIGN KEY (sponsor_id) REFERENCES sponsors (id);
ALTER TABLE sponsor_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_group FOREIGN KEY (group_id) REFERENCES affinity_groups (id);

CREATE TABLE component_abilities (
	component_id		INTEGER,
  	ability_id			INTEGER
);
ALTER TABLE component_abilities ADD CONSTRAINT fk_component_abilities_ability FOREIGN KEY (ability_id) REFERENCES abilities (id);
ALTER TABLE component_abilities ADD CONSTRAINT fk_component_abilities_component FOREIGN KEY (component_id) REFERENCES unit_components (id);

CREATE TABLE composite_unit_components (
	unit_id				INTEGER,
  	component_id		INTEGER
);
ALTER TABLE composite_unit_components ADD CONSTRAINT fk_composite_unit_components_unit FOREIGN KEY (unit_id) REFERENCES units (id);
ALTER TABLE composite_unit_components ADD CONSTRAINT fk_composite_unit_components_component FOREIGN KEY (component_id) REFERENCES unit_components (id);

CREATE TABLE sponsor_affinity_avas_affinity_groups (
	sponsor_affinity_ava_id	INTEGER,
  	affinity_id			VARCHAR(30)
);
ALTER TABLE sponsor_affinity_avas_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_ava FOREIGN KEY (sponsor_affinity_ava_id) REFERENCES sponsor_affinity_avas (id);
ALTER TABLE sponsor_affinity_avas_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_affinity FOREIGN KEY (affinity_id) REFERENCES affinity_groups (id);
