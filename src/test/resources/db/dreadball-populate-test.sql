INSERT INTO abilities (id, name) VALUES (1, 'ability1');
INSERT INTO abilities (id, name) VALUES (2, 'ability2');
INSERT INTO abilities (id, name) VALUES (3, 'ability3');
INSERT INTO abilities (id, name) VALUES (4, 'ability4');

INSERT INTO affinity_groups (id, name) VALUES (1, 'group1');
INSERT INTO affinity_groups (id, name) VALUES (2, 'group2');
INSERT INTO affinity_groups (id, name) VALUES (3, 'group3');
INSERT INTO affinity_groups (id, name) VALUES (4, 'group4');
INSERT INTO affinity_groups (id, name) VALUES (5, 'group5');

INSERT INTO sponsors (id, name) VALUES (1, 'sponsor1');
INSERT INTO sponsors (id, name) VALUES (2, 'sponsor2');

INSERT INTO units (id, name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (1, 'unit1', 11, 1, 2, 3, 4, 5, 'STRIKER', false);
INSERT INTO units (id, name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (2, 'unit2', 11, 1, 2, 3, 4, 5, 'JACK', true);
INSERT INTO units (id, name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (3, 'unit3', 11, 1, 2, 3, 4, 5, 'GUARD', false);

INSERT INTO affinity_units (id, name, cost, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (1, 'affunit1', 11, 1, 2, 3, 4, 5, 'STRIKER', false, 6, 7, 8);
INSERT INTO affinity_units (id, name, cost, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (2, 'affunit2', 11, 1, 2, 3, 4, 5, 'JACK', true, 6, 7, 8);
INSERT INTO affinity_units (id, name, cost, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (3, 'affunit3', 11, 1, 2, 3, 4, 5, 'GUARD', false, 6, 7, 8);

INSERT INTO sponsor_affinity_groups (sponsor_id, group_id) VALUES (1, 2);
INSERT INTO sponsor_affinity_groups (sponsor_id, group_id) VALUES (1, 3);

INSERT INTO unit_abilities (unit_id, ability_id) VALUES (1, 2);
INSERT INTO unit_abilities (unit_id, ability_id) VALUES (2, 3);
INSERT INTO unit_abilities (unit_id, ability_id) VALUES (2, 4);

INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (1, 2);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (1, 3);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (2, 1);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (3, 3);