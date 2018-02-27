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

package com.bernardomg.tabletop.dreadball.model.persistence.availability.asset;

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

import com.bernardomg.tabletop.dreadball.model.availability.asset.TeamTypeAssetsAvailability;
import com.bernardomg.tabletop.dreadball.model.faction.TeamType;
import com.bernardomg.tabletop.dreadball.model.persistence.faction.PersistentTeamType;
import com.google.common.base.MoreObjects;

/**
 * Assets availabilities for a {@link TeamType}, which are the Dreadball
 * Original (DBO) factions.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "TeamTypeAssetsAvailability")
@Table(name = "team_type_asset_avas")
public final class PersistentTeamTypeAssetsAvailability
        implements TeamTypeAssetsAvailability, Serializable {

    /**
     * Serialization ID.
     */
    private static final long  serialVersionUID                   = -5016337593543278027L;

    /**
     * Cheerleader cost.
     */
    @Column(name = "cost_cheerleader")
    private Integer            cheerleaderCost                    = 0;

    /**
     * Initial number of cheerleaders for the team.
     */
    @Column(name = "initial_cheerleader")
    private Integer            cheerleaderInitial                 = 0;

    /**
     * Maximum number of cheerleaders for the team.
     */
    @Column(name = "max_cheerleader")
    private Integer            cheerleaderMax                     = 0;

    /**
     * Coaching dice cost.
     */
    @Column(name = "cost_dice")
    private Integer            coachingDieCost                    = 0;

    /**
     * Initial number of coaching dice for the team.
     */
    @Column(name = "initial_dice")
    private Integer            coachingDieInitial                 = 0;

    /**
     * Maximum number of Coaching dice for the team.
     */
    @Column(name = "max_dice")
    private Integer            coachingDieMax                     = 0;

    /**
     * Coaching staff cost.
     */
    @Column(name = "cost_coaching")
    private Integer            coachingStaffCost                  = 0;

    /**
     * Dreadball card cost.
     */
    @Column(name = "cost_card")
    private Integer            dreadballCardCost                  = 0;

    /**
     * Initial number of Dreadball cards for the team.
     */
    @Column(name = "initial_card")
    private Integer            dreadballCardInitial               = 0;

    /**
     * Maximum number of Dreadball cards for the team.
     */
    @Column(name = "max_card")
    private Integer            dreadballCardMax                   = 0;

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer            id                                 = -1;

    /**
     * Flag indicating if the team begins with a defensive coaching staff.
     */
    @Column(name = "def_coach")
    private Boolean            startingWithDefensiveCoachingStaff = false;

    /**
     * Flag indicating if the team begins with an offensive coaching staff.
     */
    @Column(name = "off_coach")
    private Boolean            startingWithOffensiveCoachingStaff = false;

    /**
     * Flag indicating if the team begins with a support coaching staff.
     */
    @Column(name = "sup_coach")
    private Boolean            startingWithSupportCoachingStaff   = false;

    /**
     * Team type for the availability.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private PersistentTeamType teamType;

    /**
     * Default constructor.
     */
    public PersistentTeamTypeAssetsAvailability() {
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

        final PersistentTeamTypeAssetsAvailability other;

        other = (PersistentTeamTypeAssetsAvailability) obj;
        return Objects.equals(teamType, other.teamType);
    }

    @Override
    public final Integer getCheerleaderCost() {
        return cheerleaderCost;
    }

    @Override
    public final Integer getCheerleaderInitial() {
        return cheerleaderInitial;
    }

    @Override
    public final Integer getCheerleaderMax() {
        return cheerleaderMax;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return coachingDieCost;
    }

    @Override
    public final Integer getCoachingDieInitial() {
        return coachingDieInitial;
    }

    @Override
    public final Integer getCoachingDieMax() {
        return coachingDieMax;
    }

    @Override
    public final Integer getCoachingStaffCost() {
        return coachingStaffCost;
    }

    @Override
    public final Integer getDreadballCardCost() {
        return dreadballCardCost;
    }

    @Override
    public final Integer getDreadballCardInitial() {
        return dreadballCardInitial;
    }

    @Override
    public final Integer getDreadballCardMax() {
        return dreadballCardMax;
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
    public final Boolean getStartingWithDefensiveCoachingStaff() {
        return startingWithDefensiveCoachingStaff;
    }

    @Override
    public final Boolean getStartingWithOffensiveCoachingStaff() {
        return startingWithOffensiveCoachingStaff;
    }

    @Override
    public final Boolean getStartingWithSupportCoachingStaff() {
        return startingWithSupportCoachingStaff;
    }

    @Override
    public final TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(teamType);
    }

    /**
     * Sets the cheerleaders cost.
     * 
     * @param cost
     *            the cheerleaders cost
     */
    public final void setCheerleadersCost(final Integer cost) {
        cheerleaderCost = cost;
    }

    /**
     * Sets the initial number of cheerleaders.
     * 
     * @param initial
     *            the initial number of cheerleaders
     */
    public final void setCheerleadersInitial(final Integer initial) {
        cheerleaderInitial = initial;
    }

    /**
     * Sets the maximum number of cheerleaders.
     * 
     * @param max
     *            the maximum number of cheerleaders
     */
    public final void setCheerleadersMax(final Integer max) {
        cheerleaderMax = max;
    }

    /**
     * Sets the coaching dice cost.
     * 
     * @param cost
     *            the coaching dice cost
     */
    public final void setCoachingDieCost(final Integer cost) {
        coachingDieCost = cost;
    }

    /**
     * Sets the initial number of coaching dice.
     * 
     * @param initial
     *            the initial number of coaching dice
     */
    public final void setCoachingDieInitial(final Integer initial) {
        coachingDieInitial = initial;
    }

    /**
     * Sets the maximum number of coaching dice.
     * 
     * @param max
     *            the maximum number of coaching dice
     */
    public final void setCoachingDieMax(final Integer max) {
        coachingDieMax = max;
    }

    /**
     * Sets the coaching staff cost.
     * 
     * @param cost
     *            the coaching staff cost
     */
    public final void setCoachingStaffCost(final Integer cost) {
        coachingStaffCost = cost;
    }

    /**
     * Sets the Dreadball cards cost.
     * 
     * @param cost
     *            the Dreadball cards cost
     */
    public final void setDreadballCardCost(final Integer cost) {
        dreadballCardCost = cost;
    }

    /**
     * Sets the initial number of Dreadball cards.
     * 
     * @param initial
     *            the initial number of Dreadball cards
     */
    public final void setDreadballCardInitial(final Integer initial) {
        dreadballCardInitial = initial;
    }

    /**
     * Sets the maximum number of Dreadball cards.
     * 
     * @param max
     *            the maximum number of Dreadball cards
     */
    public final void setDreadballCardMax(final Integer max) {
        dreadballCardMax = max;
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
     * Sets the flag indicating if the team begins with a defensive coaching
     * staff.
     * 
     * @param coach
     *            the flag indicating if the team begins with a defensive
     *            coaching staff
     */
    public final void
            setStartingWithDefensiveCoachingStaff(final Boolean coach) {
        startingWithDefensiveCoachingStaff = coach;
    }

    /**
     * Sets the flag indicating if the team begins with an offensive coaching
     * staff.
     * 
     * @param coach
     *            the flag indicating if the team begins with an offensive
     *            coaching staff
     */
    public final void
            setStartingWithOffensiveCoachingStaff(final Boolean coach) {
        startingWithOffensiveCoachingStaff = coach;
    }

    /**
     * Sets the flag indicating if the team begins with a support coaching
     * staff.
     * 
     * @param coach
     *            the flag indicating if the team begins with a support coaching
     *            staff
     */
    public final void setStartingWithSupportCoachingStaff(final Boolean coach) {
        startingWithSupportCoachingStaff = coach;
    }

    /**
     * Sets the availability's team type.
     * 
     * @param team
     *            the availability's team type
     */
    public final void setTeamType(final PersistentTeamType team) {
        teamType = team;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
