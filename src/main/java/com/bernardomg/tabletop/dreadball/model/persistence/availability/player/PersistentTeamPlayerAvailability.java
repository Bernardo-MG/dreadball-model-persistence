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

package com.bernardomg.tabletop.dreadball.model.persistence.availability.player;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.availability.player.TeamPlayerAvailability;
import com.bernardomg.tabletop.dreadball.model.faction.TeamType;
import com.bernardomg.tabletop.dreadball.model.persistence.faction.PersistentTeamType;
import com.bernardomg.tabletop.dreadball.model.persistence.player.PersistentTeamPlayer;
import com.bernardomg.tabletop.dreadball.model.player.TeamPlayer;
import com.google.common.base.MoreObjects;

/**
 * Player availabilities for a {@link TeamType}, to be used for both Dreadball
 * Original (DBO) and Dreadball Xtreme (DBX).
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "TeamTypePlayerAvailability")
@Table(name = "TEAM_TYPE_PLAYERS")
public class PersistentTeamPlayerAvailability
        implements TeamPlayerAvailability, Serializable {

    /**
     * Serialization ID.
     */
    private static final long    serialVersionUID = -3725739240261330858L;

    /**
     * Ava's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer              id               = -1;

    /**
     * Player for the availability.
     */
    @OneToOne
    @JoinColumn(name = "player_id")
    private PersistentTeamPlayer teamPlayer;

    /**
     * Team type for the availability.
     */
    @OneToOne
    @JoinColumn(name = "team_type_id")
    private PersistentTeamType   teamType;

    /**
     * Default constructor.
     */
    public PersistentTeamPlayerAvailability() {
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

        final PersistentTeamPlayerAvailability other;

        other = (PersistentTeamPlayerAvailability) obj;
        return Objects.equals(teamType, other.teamType)
                && Objects.equals(teamPlayer, other.teamPlayer);
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
    public TeamPlayer getTeamPlayer() {
        return teamPlayer;
    }

    @Override
    public TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(teamPlayer, teamType);
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
     * Sets the player for the availability.
     * 
     * @param playerType
     *            the player for the availability
     */
    public void setTeamPlayer(final PersistentTeamPlayer playerType) {
        teamPlayer = playerType;
    }

    /**
     * Sets the team type for the availability.
     * 
     * @param team
     *            the team type for the availability
     */
    public void setTeamType(final PersistentTeamType team) {
        teamType = team;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("team", teamType)
                .add("player", teamPlayer).toString();
    }

}
