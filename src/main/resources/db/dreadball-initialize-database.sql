DROP TABLE abilities IF EXISTS;
DROP TABLE sponsors IF EXISTS;
DROP TABLE units IF EXISTS;
DROP TABLE affinity_groups IF EXISTS;

DROP TABLE unit_abilities IF EXISTS;
DROP TABLE sponsor_affinity_groups IF EXISTS;

CREATE TABLE abilities (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30)
);

CREATE TABLE sponsors (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cash			INTEGER DEFAULT 0,
	rank			INTEGER DEFAULT 0
);

CREATE TABLE units (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30),
	cost			INTEGER DEFAULT 0,
	armor			INTEGER DEFAULT 0,
	movement		INTEGER DEFAULT 0,
	skill			INTEGER DEFAULT 0,
	speed			INTEGER DEFAULT 0,
	strength		INTEGER DEFAULT 0,
	position		VARCHAR(30),
	giant			BOOLEAN DEFAULT FALSE
);

CREATE TABLE affinity_groups (
	id				INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR(30)
);

CREATE TABLE unit_abilities (
	template_id		INTEGER,
  	ability_id		INTEGER
);
ALTER TABLE unit_abilities ADD CONSTRAINT fk_unit_abilities_ability FOREIGN KEY (ability_id) REFERENCES abilities (id);
ALTER TABLE unit_abilities ADD CONSTRAINT fk_unit_abilities_template FOREIGN KEY (template_id) REFERENCES units (id);

CREATE TABLE sponsor_affinity_groups (
	sponsor_id		INTEGER,
  	group_id		INTEGER
);
ALTER TABLE sponsor_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_sponsor FOREIGN KEY (sponsor_id) REFERENCES sponsors (id);
ALTER TABLE sponsor_affinity_groups ADD CONSTRAINT fk_sponsor_affinity_groups_group FOREIGN KEY (group_id) REFERENCES affinity_groups (id);