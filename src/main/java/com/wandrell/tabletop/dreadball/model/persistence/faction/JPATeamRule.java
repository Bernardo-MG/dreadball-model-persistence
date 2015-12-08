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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.faction.TeamRule;

/**
 * Persistent JPA-based implementation of {@link TeamRule}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "TeamRule")
@Table(name = "team_rules")
public final class JPATeamRule
        implements TeamRule, PersistenceEntity, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 3815826961091481042L;
    /**
     * Team rule's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    /**
     * Team rule name.
     */
    @Column(name = "name", unique = true)
    private String            name             = "";

    /**
     * Constructs a {@code JPATeamRule}.
     */
    public JPATeamRule() {
        super();
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

        final JPATeamRule other;

        other = (JPATeamRule) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

    /**
     * Sets the team rule name.
     * 
     * @param ruleName
     *            the team rule name
     */
    public final void setTeamRuleName(final String ruleName) {
        checkNotNull(ruleName, "Received a null pointer as name");

        name = ruleName;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
