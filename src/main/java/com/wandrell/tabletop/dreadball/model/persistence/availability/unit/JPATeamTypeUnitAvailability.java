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
package com.wandrell.tabletop.dreadball.model.persistence.availability.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeUnitAvailability;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnitTemplate;
import com.wandrell.tabletop.dreadball.model.unit.UnitTemplate;

/**
 * Persistent JPA-based implementation of {@link TeamType}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "TeamTypeUnitAvailability")
@Table(name = "team_type_unit_avas")
public final class JPATeamTypeUnitAvailability
        implements TeamTypeUnitAvailability, PersistenceEntity, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -3725739240261330858L;
    /**
     * Ava's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    /**
     * Initial number of units the team has.
     */
    @Column(name = "initial")
    private Integer           initial          = 0;
    /**
     * Maximum number of units the team can have.
     */
    @Column(name = "max")
    private Integer           max              = 0;
    /**
     * Team type for the availability.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private JPATeamType       teamType;
    /**
     * Unit for the availability.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private JPAUnitTemplate   unit;

    /**
     * Constructs a {@code JPATeamTypeUnitAvailability}.
     */
    public JPATeamTypeUnitAvailability() {
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

        final JPATeamTypeUnitAvailability other;

        other = (JPATeamTypeUnitAvailability) obj;
        return Objects.equals(teamType, other.teamType)
                && Objects.equals(unit, other.unit);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getInitialNumber() {
        return initial;
    }

    @Override
    public final Integer getMaxNumber() {
        return max;
    }

    @Override
    public final TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final UnitTemplate getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unit, teamType);
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

    /**
     * Sets the initial number of units for the team type.
     * 
     * @param initialCount
     *            the initial number of units for the team type
     */
    public final void setInitialNumber(final Integer initialCount) {
        checkNotNull(initialCount, "Received a null pointer as initial count");

        initial = initialCount;
    }

    /**
     * Sets the max number of units for the team type.
     * 
     * @param maxCount
     *            the max number of units for the team type
     */
    public final void setMaxNumber(final Integer maxCount) {
        checkNotNull(maxCount, "Received a null pointer as max count");

        max = maxCount;
    }

    /**
     * Sets the team type for the availability.
     * 
     * @param team
     *            the team type for the availability
     */
    public final void setTeamType(final JPATeamType team) {
        checkNotNull(team, "Received a null pointer as team type");

        teamType = team;
    }

    /**
     * Sets the unit for the availability.
     * 
     * @param unitType
     *            the unit for the availability
     */
    public final void setUnit(final JPAUnitTemplate unitType) {
        checkNotNull(unitType, "Received a null pointer as unit");

        unit = unitType;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("team", teamType)
                .add("unit", unit).toString();
    }

}
