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

package com.wandrell.tabletop.dreadball.model.persistence.availability.faction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wandrell.tabletop.dreadball.model.availability.faction.TeamTypeSeason;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;

/**
 * Season in which a team appeared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "PersistentTeamTypeSeason")
@Table(name = "team_type_seasons")
public final class PersistentTeamTypeSeason implements TeamTypeSeason {

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id           = -1;

    @Column(name = "season")
    private Integer  seasonNumber = 0;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private TeamType team;

    public PersistentTeamTypeSeason() {
        super();
    }

    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public final TeamType getTeam() {
        return team;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setSeasonNumber(final Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public final void setTeam(final TeamType teamType) {
        this.team = teamType;
    }

}
