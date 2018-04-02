/**
 * Copyright 2015-2016 the original author or authors
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

package com.bernardomg.tabletop.dreadball.model.persistence.availability.faction;

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

import com.bernardomg.tabletop.dreadball.model.availability.faction.TeamTypeSeason;
import com.bernardomg.tabletop.dreadball.model.persistence.faction.PersistentTeamType;

/**
 * Season in which a team appeared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "PersistentTeamTypeSeason")
@Table(name = "TEAM_TYPE_SEASONS")
public final class PersistentTeamTypeSeason
        implements TeamTypeSeason, Serializable {

    /**
     * Serialization id.
     */
    private static final long  serialVersionUID = 2784127994970421161L;

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id               = -1;

    /**
     * The season in which the team type appeared.
     */
    @Column(name = "season")
    private Integer            seasonNumber     = 0;

    /**
     * The team which appeared in this season.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private PersistentTeamType team;

    /**
     * Default constructor.
     */
    public PersistentTeamTypeSeason() {
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
        final PersistentTeamTypeSeason other = (PersistentTeamTypeSeason) obj;
        return Objects.equals(team, other.team)
                && Objects.equals(seasonNumber, other.seasonNumber);
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
    public final Integer getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public final PersistentTeamType getTeam() {
        return team;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(team, seasonNumber);
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public final void setId(final Integer identifier) {
        id = identifier;
    }

    /**
     * Sets the season number.
     * 
     * @param season
     *            the season number
     */
    public final void setSeasonNumber(final Integer season) {
        seasonNumber = season;
    }

    /**
     * Sets the team.
     * 
     * @param teamType
     *            the team
     */
    public final void setTeam(final PersistentTeamType teamType) {
        team = teamType;
    }

}
