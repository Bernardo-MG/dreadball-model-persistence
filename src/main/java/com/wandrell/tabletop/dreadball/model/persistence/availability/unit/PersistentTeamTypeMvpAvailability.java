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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeMvpAvailability;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.PersistentTeamType;
import com.wandrell.tabletop.dreadball.model.persistence.unit.PersistentUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

/**
 * MVP availabilities for a {@link TeamType}, which are the Dreadball Original
 * (DBO) factions.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "TeamTypeMvpAvailability")
@Table(name = "team_type_mvp_avas")
public final class PersistentTeamTypeMvpAvailability
        implements TeamTypeMvpAvailability, Serializable {

    /**
     * Serialization ID.
     */
    private static final long  serialVersionUID = -6257561802154856009L;

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id               = -1;

    /**
     * Team type for the availability.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private PersistentTeamType teamType;

    /**
     * Unit for the availability.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private PersistentUnit     unit;

    /**
     * Constructs a {@code JPATeamTypeMvpAvailability}.
     */
    public PersistentTeamTypeMvpAvailability() {
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

        final PersistentTeamTypeMvpAvailability other;

        other = (PersistentTeamTypeMvpAvailability) obj;
        return Objects.equals(teamType, other.teamType)
                && Objects.equals(unit, other.unit);
    }

    /**
     * Returns the ID assigned to this entity.
     * 
     * @return the entity's ID
     */
    public final Integer getId() {
        return id;
    }

    @Override
    public final TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unit, teamType);
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as id");

        id = identifier;
    }

    /**
     * Sets the team type for the availability.
     * 
     * @param team
     *            the team type for the availability
     */
    public final void setTeamType(final PersistentTeamType team) {
        checkNotNull(team, "Received a null pointer as team type");

        teamType = team;
    }

    /**
     * Sets the unit for the availability.
     * 
     * @param unitType
     *            the unit for the availability
     */
    public final void setUnit(final PersistentUnit unitType) {
        checkNotNull(unitType, "Received a null pointer as unit");

        unit = unitType;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("team", teamType)
                .add("unit", unit).toString();
    }

}
