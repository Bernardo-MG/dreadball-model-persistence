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
package com.wandrell.tabletop.dreadball.model.persistence.faction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.faction.TeamRule;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;

/**
 * Persistent JPA-based implementation of {@link TeamType}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "TeamType")
@Table(name = "team_types")
public final class JPATeamType
        implements TeamType, PersistenceEntity, Serializable {

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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "team_type_rules",
            joinColumns = { @JoinColumn(name = "team_type_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "team_rule_id",
                    referencedColumnName = "id") })
    private final Collection<JPATeamRule> rules            = new LinkedHashSet<JPATeamRule>();

    /**
     * Constructs a {@code JPATeamType}.
     */
    public JPATeamType() {
        super();
    }

    /**
     * Adds a team rule.
     * 
     * @param rule
     *            the team rule to add
     */
    public final void addTeamRule(final TeamRule rule) {
        checkNotNull(rule, "Received a null pointer as rule");
        checkArgument(rule instanceof JPATeamRule,
                "The TeamRule should be an instanceof JPATeamRule");

        getTeamRulesModifiable().add((JPATeamRule) rule);
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

        final JPATeamType other;

        other = (JPATeamType) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Collection<TeamRule> getTeamRules() {
        final Collection<TeamRule> col;

        col = new LinkedList<>();
        for (final TeamRule rule : getTeamRulesModifiable()) {
            col.add(rule);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final String getTeamTypeName() {
        return name;
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

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

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
    public final void setTeamRules(final Collection<TeamRule> teamRules) {
        checkNotNull(teamRules, "Received a null pointer as rules");

        getTeamRulesModifiable().clear();

        for (final TeamRule rule : teamRules) {
            checkArgument(rule instanceof JPATeamRule,
                    "All the rules should be an instanceof JPATeamRule");

            getTeamRulesModifiable().add((JPATeamRule) rule);
        }
    }

    /**
     * Sets the team type name.
     * 
     * @param typeName
     *            team type name
     */
    public final void setTeamTypeName(final String typeName) {
        checkNotNull(typeName, "Received a null pointer as name");

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
    private final Collection<JPATeamRule> getTeamRulesModifiable() {
        return rules;
    }

}
