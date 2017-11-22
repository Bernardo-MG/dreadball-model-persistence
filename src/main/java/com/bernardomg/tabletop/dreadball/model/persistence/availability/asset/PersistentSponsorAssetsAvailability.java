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
import com.bernardomg.tabletop.dreadball.model.availability.asset.SponsorAssetsAvailability;

/**
 * Asset availabilities for a
 * {@link com.bernardomg.tabletop.dreadball.model.faction.Sponsor Sponsors}.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "SponsorAssetsAvailability")
@Table(name = "sponsor_asset_avas")
public final class PersistentSponsorAssetsAvailability
        implements SponsorAssetsAvailability, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -7367447173870314061L;

    /**
     * Cost of an affinity group.
     */
    @Column(name = "cost_affinity")
    private Integer           costAffinity     = 0;

    /**
     * Cost of a cheerleader.
     */
    @Column(name = "cost_cheerleader")
    private Integer           costCheerleader  = 0;

    /**
     * Cost of unlocking the cheerleaders.
     */
    @Column(name = "cost_cheerleader_unlock")
    private Integer           costCheerUnlock  = 0;

    /**
     * Cost of a coaching dice.
     */
    @Column(name = "cost_dice")
    private Integer           costDice         = 0;

    /**
     * Cost of a medibot.
     */
    @Column(name = "cost_medibot")
    private Integer           costMedibot      = 0;

    /**
     * Cost of a sabotage card.
     */
    @Column(name = "cost_sabotage")
    private Integer           costSabotage     = 0;

    /**
     * Cost of a special movement card.
     */
    @Column(name = "cost_special_move")
    private Integer           costSpecialMove  = 0;

    /**
     * Cost of a wager.
     */
    @Column(name = "cost_wager")
    private Integer           costWager        = 0;

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;

    /**
     * Maximum number of wagers.
     */
    @Column(name = "max_wager")
    private Integer           maxWager         = 0;

    /**
     * Minimum required cost for a team.
     */
    @Column(name = "min_team_cost")
    private Integer           minTeamCost      = 0;

    /**
     * Default constructor.
     */
    public PersistentSponsorAssetsAvailability() {
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

        final PersistentSponsorAssetsAvailability other;

        other = (PersistentSponsorAssetsAvailability) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Integer getAffinityGroupCost() {
        return costAffinity;
    }

    @Override
    public final Integer getCheerleaderCost() {
        return costCheerleader;
    }

    @Override
    public final Integer getCheerleaderUnlockCost() {
        return costCheerUnlock;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return costDice;
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
    public final Integer getMediBotCost() {
        return costMedibot;
    }

    @Override
    public final Integer getSabotageCardCost() {
        return costSabotage;
    }

    @Override
    public final Integer getSpecialMovementCardCost() {
        return costSpecialMove;
    }

    @Override
    public final Integer getTeamCostMin() {
        return minTeamCost;
    }

    @Override
    public final Integer getWagerCost() {
        return costWager;
    }

    @Override
    public final Integer getWagerMaxCount() {
        return maxWager;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Sets the cost of an affinity group.
     * 
     * @param cost
     *            the cost of an affinity group
     */
    public final void setAffinityGroupCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costAffinity = cost;
    }

    /**
     * Sets the cost of a cheerleader.
     * 
     * @param cost
     *            the cost of a cheerleader
     */
    public final void setCheerleaderCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCheerleader = cost;
    }

    /**
     * Sets the cost of unlocking the cheerleaders.
     * 
     * @param cost
     *            the cost of unlocking the cheerleaders
     */
    public final void setCheerleaderUnlockCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCheerUnlock = cost;
    }

    /**
     * Sets the cost of a coaching die.
     * 
     * @param cost
     *            the cost of a coaching die
     */
    public final void setCoachingDieCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costDice = cost;
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
     * Sets the cost of a medibot.
     * 
     * @param cost
     *            the cost of a medibot
     */
    public final void setMediBotCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costMedibot = cost;
    }

    /**
     * Sets the cost of a sabotage card.
     * 
     * @param cost
     *            the cost of a sabotage card
     */
    public final void setSabotageCardCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costSabotage = cost;
    }

    /**
     * Sets the cost of a special movement card.
     * 
     * @param cost
     *            the cost of a special movement card
     */
    public final void setSpecialMovementCardCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costSpecialMove = cost;
    }

    /**
     * Sets the minimum allowed cost for a team.
     * 
     * @param cost
     *            the minimum allowed cost for a team
     */
    public final void setTeamCostMin(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        minTeamCost = cost;
    }

    /**
     * Sets the cost of a wager.
     * 
     * @param cost
     *            the cost of a wager
     */
    public final void setWagerCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costWager = cost;
    }

    /**
     * Sets the maximum number of wagers.
     * 
     * @param max
     *            the maximum number of wagers
     */
    public final void setWagerMaxCount(final Integer max) {
        checkNotNull(max,
                "Received a null pointer as maximum number of wagers");

        maxWager = max;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
