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
package com.wandrell.tabletop.dreadball.model.persistence.availability.team;

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
import com.wandrell.tabletop.dreadball.model.availability.team.TeamTypeAssetsAvailability;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;

@Entity(name = "TeamTypeAssetsAvailability")
@Table(name = "team_type_asset_avas")
public final class JPATeamTypeAssetsAvailability
        implements TeamTypeAssetsAvailability, PersistenceEntity, Serializable {

    private static final long serialVersionUID   = -5016337593543278027L;
    @Column(name = "cost_card")
    private Integer           costCard           = 0;
    @Column(name = "cost_cheerleader")
    private Integer           costCheerleader    = 0;
    @Column(name = "cost_coaching")
    private Integer           costCoaching       = 0;
    @Column(name = "cost_dice")
    private Integer           costDice           = 0;
    @Column(name = "def_coach")
    private Boolean           defCoach           = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id                 = -1;
    @Column(name = "initial_card")
    private Integer           initialCard        = 0;
    @Column(name = "initial_cheerleader")
    private Integer           initialCheerleader = 0;
    @Column(name = "initial_dice")
    private Integer           initialDice        = 0;
    @Column(name = "max_card")
    private Integer           maxCard            = 0;
    @Column(name = "max_cheerleader")
    private Integer           maxCheerleader     = 0;
    @Column(name = "max_dice")
    private Integer           maxDice            = 0;
    @Column(name = "off_coach")
    private Boolean           offCoach           = false;
    @Column(name = "sup_coach")
    private Boolean           supCoach           = false;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private JPATeamType       teamType;

    public JPATeamTypeAssetsAvailability() {
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

        final JPATeamTypeAssetsAvailability other;

        other = (JPATeamTypeAssetsAvailability) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Integer getCheerleadersCost() {
        return costCheerleader;
    }

    @Override
    public final Integer getCheerleadersInitial() {
        return initialCheerleader;
    }

    @Override
    public final Integer getCheerleadersMax() {
        return maxCheerleader;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return costDice;
    }

    @Override
    public final Integer getCoachingDieInitial() {
        return initialDice;
    }

    @Override
    public final Integer getCoachingDieMax() {
        return maxDice;
    }

    @Override
    public final Integer getCoachingStaffCost() {
        return costCoaching;
    }

    @Override
    public final Integer getDreadballCardCost() {
        return costCard;
    }

    @Override
    public final Integer getDreadballCardInitial() {
        return initialCard;
    }

    @Override
    public final Integer getDreadballCardMax() {
        return maxCard;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final Boolean isStartingWithDefensiveCoachingStaff() {
        return defCoach;
    }

    @Override
    public final Boolean isStartingWithOffensiveCoachingStaff() {
        return offCoach;
    }

    @Override
    public final Boolean isStartingWithSupportCoachingStaff() {
        return supCoach;
    }

    public final void setCheerleadersCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCheerleader = cost;
    }

    public final void setCheerleadersInitial(final Integer initial) {
        checkNotNull(initial, "Received a null pointer as initial count");

        initialCheerleader = initial;
    }

    public final void setCheerleadersMax(final Integer max) {
        checkNotNull(max, "Received a null pointer as max count");

        maxCheerleader = max;
    }

    public final void setCoachingDieCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costDice = cost;
    }

    public final void setCoachingDieInitial(final Integer initial) {
        checkNotNull(initial, "Received a null pointer as initial count");

        initialDice = initial;
    }

    public final void setCoachingDieMax(final Integer max) {
        checkNotNull(max, "Received a null pointer as max count");

        maxDice = max;
    }

    public final void setCoachingStaffCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCoaching = cost;
    }

    public final void setDreadballCardCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCard = cost;
    }

    public final void setDreadballCardInitial(final Integer initial) {
        checkNotNull(initial, "Received a null pointer as initial count");

        initialCard = initial;
    }

    public final void setDreadballCardMax(final Integer max) {
        checkNotNull(max, "Received a null pointer as max count");

        maxCard = max;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void
            setStartingWithDefensiveCoachingStaff(final Boolean coach) {
        checkNotNull(coach, "Received a null pointer as coach flag");

        defCoach = coach;
    }

    public final void
            setStartingWithOffensiveCoachingStaff(final Boolean coach) {
        checkNotNull(coach, "Received a null pointer as coach flag");

        offCoach = coach;
    }

    public final void setStartingWithSupportCoachingStaff(final Boolean coach) {
        checkNotNull(coach, "Received a null pointer as coach flag");

        supCoach = coach;
    }

    public final void setTeamType(final JPATeamType team) {
        checkNotNull(team, "Received a null pointer as team type");

        this.teamType = team;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
