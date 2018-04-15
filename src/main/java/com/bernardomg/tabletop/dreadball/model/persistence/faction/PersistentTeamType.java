/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bernardomg.tabletop.dreadball.model.persistence.faction;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.faction.TeamRule;
import com.bernardomg.tabletop.dreadball.model.faction.TeamType;
import com.google.common.base.MoreObjects;

/**
 * Team type, which are the Dreadball Original (DBO) factions. They are also
 * used to group together Dreadball Xtreme (DBX) players.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "TeamType")
@Table(name = "TEAM_TYPES")
public final class PersistentTeamType implements TeamType, Serializable {

    /**
     * Serialization ID.
     */
    private static final long             serialVersionUID = -6484889622281976716L;

    /**
     * Team type's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                       id               = -1;

    /**
     * Team type name.
     */
    @Column(name = "name", unique = true)
    private String                        name             = "";

    /**
     * Team type rules.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TEAM_TYPE_RULES",
            joinColumns = { @JoinColumn(name = "team_type_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "team_rule_id",
                    referencedColumnName = "id") })
    private final Set<PersistentTeamRule> rules            = new HashSet<>();

    /**
     * Default constructor.
     */
    public PersistentTeamType() {
        super();
    }

    /**
     * Adds a team rule.
     * 
     * @param rule
     *            the team rule to add
     */
    public final void addTeamRule(final TeamRule rule) {
        checkArgument(rule instanceof PersistentTeamRule,
                "The TeamRule should be an instanceof JPATeamRule");

        getTeamRulesModifiable().add((PersistentTeamRule) rule);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final PersistentTeamType other;

        other = (PersistentTeamType) obj;
        return Objects.equals(name, other.name);
    }

    /**
     * Returns the ID assigned to this entity.
     * 
     * @return the entity's ID
     */
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<TeamRule> getTeamRules() {
        final Collection<TeamRule> col;

        col = new ArrayList<>();
        for (final TeamRule rule : getTeamRulesModifiable()) {
            col.add(rule);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    /**
     * Removes a team rule.
     * 
     * @param rule
     *            the team rule to remove
     */
    public final void removeTeamRule(final TeamRule rule) {
        getTeamRulesModifiable().remove(rule);
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public void setId(final Integer identifier) {
        id = identifier;
    }

    /**
     * Sets the team type team rules.
     * <p>
     * All the team rules which the team type currently has will be removed and
     * swapped with the received ones.
     * 
     * @param teamRules
     *            the team rules to set on the team type
     */
    public void setTeamRules(final Collection<TeamRule> teamRules) {
        getTeamRulesModifiable().clear();

        if (teamRules != null) {
            for (final TeamRule rule : teamRules) {
                checkArgument(rule instanceof PersistentTeamRule,
                        "All the rules should be an instanceof JPATeamRule");

                getTeamRulesModifiable().add((PersistentTeamRule) rule);
            }
        }
    }

    /**
     * Sets the team type name.
     * 
     * @param typeName
     *            team type name
     */
    public void setTeamTypeName(final String typeName) {
        name = typeName;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    /**
     * Returns the modifiable collection of the team type's team rules.
     * 
     * @return the modifiable collection of the team type's team rules
     */
    private final Collection<PersistentTeamRule> getTeamRulesModifiable() {
        return rules;
    }

}
