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

INSERT INTO team_rules (id, name) VALUES (1, 'team_rule1');
INSERT INTO team_rules (id, name) VALUES (2, 'team_rule2');

INSERT INTO team_types (id, name) VALUES (1, 'team_type1');
INSERT INTO team_types (id, name) VALUES (2, 'team_type2');
INSERT INTO team_types (id, name) VALUES (3, 'team_type3');

INSERT INTO team_type_rules (team_type_id, team_rule_id) VALUES (2, 1);

INSERT INTO units (id, template_name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (1, 'unit1', 11, 1, 2, 3, 4, 5, 'STRIKER', false);
INSERT INTO units (id, template_name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (2, 'unit2', 11, 1, 2, 3, 4, 5, 'JACK', true);
INSERT INTO units (id, template_name, cost, armor, movement, skill, speed, strength, position, giant)
	VALUES (3, 'unit3', 11, 1, 2, 3, 4, 5, 'GUARD', false);

INSERT INTO affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (1, 'affunit1', '', 1, 2, 3, 4, 5, 'STRIKER', false, 6, 7, 8);
INSERT INTO affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (2, 'affunit2', '', 1, 2, 3, 4, 5, 'JACK', true, 6, 7, 8);
INSERT INTO affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (3, 'affunit3', '', 1, 2, 3, 4, 5, 'GUARD', false, 6, 7, 8);

INSERT INTO composite_affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (1, 'caffunit1', '', 1, 2, 3, 4, 5, 'STRIKER', false, 6, 7, 8);
INSERT INTO composite_affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (2, 'caffunit2', '', 1, 2, 3, 4, 5, 'JACK', true, 6, 7, 8);
INSERT INTO composite_affinity_units (id, template_name, name, armor, movement, skill, speed, strength, position, giant, cost_ally, cost_friend, cost_stranger)
	VALUES (3, 'caffunit3', '', 1, 2, 3, 4, 5, 'GUARD', false, 6, 7, 8);

INSERT INTO sponsor_affinity_groups (sponsor_id, group_id) VALUES (1, 2);
INSERT INTO sponsor_affinity_groups (sponsor_id, group_id) VALUES (1, 3);

INSERT INTO unit_abilities (unit_id, ability_id) VALUES (1, 2);
INSERT INTO unit_abilities (unit_id, ability_id) VALUES (2, 3);
INSERT INTO unit_abilities (unit_id, ability_id) VALUES (2, 4);

INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (1, 2);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (1, 3);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (2, 1);
INSERT INTO unit_affinities (unit_id, affinity_id) VALUES (3, 3);

INSERT INTO unit_hated_affinities (unit_id, affinity_id) VALUES (1, 1);

INSERT INTO component_locations (id, name) VALUES (1, 'location1');
INSERT INTO component_locations (id, name) VALUES (2, 'location2');
INSERT INTO component_locations (id, name) VALUES (3, 'location3');

INSERT INTO unit_components (id, name, location_id, cost, armor, movement, skill, speed, strength) 
	VALUES (1, 'component1', 1, 1, 2, 3, 4, 5, 6);
INSERT INTO unit_components (id, name, location_id, cost, armor, movement, skill, speed, strength) 
	VALUES (2, 'component2', 1, 1, 2, 3, 4, 5, 6);
INSERT INTO unit_components (id, name, location_id, cost, armor, movement, skill, speed, strength) 
	VALUES (3, 'component3', 1, 1, 2, 3, 4, 5, 6);

INSERT INTO composite_unit_components (unit_id, component_id) VALUES (1, 2);
INSERT INTO composite_unit_components (unit_id, component_id) VALUES (2, 2);
INSERT INTO composite_unit_components (unit_id, component_id) VALUES (3, 1);
INSERT INTO composite_unit_components (unit_id, component_id) VALUES (3, 3);

INSERT INTO affinity_unit_components (id, name, location_id, cost, armor, movement, skill, speed, strength, cost_ally, cost_friend, cost_stranger) 
	VALUES (1, 'component1', 1, 1, 2, 3, 4, 5, 6, 10, 20, 30);
INSERT INTO affinity_unit_components (id, name, location_id, cost, armor, movement, skill, speed, strength, cost_ally, cost_friend, cost_stranger) 
	VALUES (2, 'component2', 1, 1, 2, 3, 4, 5, 6, 10, 20, 30);

INSERT INTO component_abilities (component_id, ability_id) VALUES (2, 1);

INSERT INTO component_positions (component_id, position) VALUES (1, 'JACK');
INSERT INTO component_positions (component_id, position) VALUES (2, 'JACK');
INSERT INTO component_positions (component_id, position) VALUES (2, 'GUARD');

INSERT INTO advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (1, 'advunit1', '', 11, 1, 2, 3, 4, 5, 'STRIKER', false, 10, 20, 1);
INSERT INTO advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (2, 'advunit2', '', 11, 1, 2, 3, 4, 5, 'JACK', true, 10, 20, 1);
INSERT INTO advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (3, 'advunit3', '', 11, 1, 2, 3, 4, 5, 'GUARD', false, 10, 20, 1);

INSERT INTO composite_advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (1, 'cadvunit1', '', 11, 1, 2, 3, 4, 5, 'STRIKER', false, 10, 20, 1);
INSERT INTO composite_advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (2, 'cadvunit2', '', 11, 1, 2, 3, 4, 5, 'JACK', true, 10, 20, 1);
INSERT INTO composite_advancement_units (id, template_name, name, cost, armor, movement, skill, speed, strength, position, giant, experience, rank, grafted_implant_id)
	VALUES (3, 'cadvunit3', '', 11, 1, 2, 3, 4, 5, 'GUARD', false, 10, 20, 1);
	
INSERT INTO team_type_unit_avas (id, initial, max, team_type_id, unit_id) VALUES (1, 5, 10, 1, 2);

INSERT INTO team_type_mvp_avas (id, team_type_id, unit_id) VALUES (1, 1, 2);

INSERT INTO sponsor_affinity_avas (id, name, rank_increase) VALUES (1, 'A', FALSE);
INSERT INTO sponsor_affinity_avas (id, name, rank_increase) VALUES (2, 'B', TRUE);

INSERT INTO sponsor_affinity_avas_affinity_groups (sponsor_affinity_ava_id, affinity_id) VALUES (1, 2);
INSERT INTO sponsor_affinity_avas_affinity_groups (sponsor_affinity_ava_id, affinity_id) VALUES (1, 4);
INSERT INTO sponsor_affinity_avas_affinity_groups (sponsor_affinity_ava_id, affinity_id) VALUES (2, 1);

INSERT INTO team_type_asset_avas (id, team_type_id, cost_card, cost_cheerleader, cost_coaching, cost_dice, initial_card, initial_cheerleader, initial_dice, max_card, max_cheerleader, max_dice, def_coach, off_coach, sup_coach) 
	VALUES (1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, TRUE, FALSE, TRUE);

INSERT INTO sponsor_asset_avas (id, cost_affinity, cost_cheerleader, cost_cheerleader_unlock, cost_dice, cost_medibot, cost_sabotage, cost_special_move, cost_wager, max_wager, min_team_cost)
	VALUES (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);