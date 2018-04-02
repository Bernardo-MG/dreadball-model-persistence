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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.availability.asset.SponsorAssetsCosts;
import com.google.common.base.MoreObjects;

/**
 * Asset availabilities for a
 * {@link com.bernardomg.tabletop.dreadball.model.faction.Sponsor Sponsors}.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "SponsorAssetsAvailability")
@Table(name = "ASSET_COSTS")
public final class PersistentSponsorAssetsCosts
        implements SponsorAssetsCosts, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID      = -7367447173870314061L;

    /**
     * Cost of an affinity group.
     */
    @Column(name = "affinity")
    private Integer           affinityCost          = 0;

    /**
     * Cost of a cheerleader.
     */
    @Column(name = "cheerleader")
    private Integer           cheerleaderCost       = 0;

    /**
     * Cost of a coaching dice.
     */
    @Column(name = "dice")
    private Integer           diceCost              = 0;

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id                    = -1;

    /**
     * Cost of a medibot.
     */
    @Column(name = "medibot")
    private Integer           medibotCost           = 0;

    /**
     * Cost of a sabotage card.
     */
    @Column(name = "nasty_surprise")
    private Integer           nastySurpriseCardCost = 0;

    /**
     * Cost of a special movement card.
     */
    @Column(name = "special_move")
    private Integer           specialMoveCardCost   = 0;

    /**
     * Cost of a wager.
     */
    @Column(name = "wager")
    private Integer           wagerCost             = 0;

    /**
     * Default constructor.
     */
    public PersistentSponsorAssetsCosts() {
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

        final PersistentSponsorAssetsCosts other;

        other = (PersistentSponsorAssetsCosts) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Integer getAffinityGroupCost() {
        return affinityCost;
    }

    @Override
    public final Integer getCheerleaderCost() {
        return cheerleaderCost;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return diceCost;
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
        return medibotCost;
    }

    @Override
    public final Integer getNastySurpriseCardCost() {
        return nastySurpriseCardCost;
    }

    @Override
    public final Integer getSpecialMoveCardCost() {
        return specialMoveCardCost;
    }

    @Override
    public final Integer getWagerCost() {
        return wagerCost;
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
        affinityCost = cost;
    }

    /**
     * Sets the cost of a cheerleader.
     * 
     * @param cost
     *            the cost of a cheerleader
     */
    public final void setCheerleaderCost(final Integer cost) {
        cheerleaderCost = cost;
    }

    /**
     * Sets the cost of a coaching die.
     * 
     * @param cost
     *            the cost of a coaching die
     */
    public final void setCoachingDieCost(final Integer cost) {
        diceCost = cost;
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
     * Sets the cost of a medibot.
     * 
     * @param cost
     *            the cost of a medibot
     */
    public final void setMediBotCost(final Integer cost) {
        medibotCost = cost;
    }

    /**
     * Sets the cost of a sabotage card.
     * 
     * @param cost
     *            the cost of a sabotage card
     */
    public final void setSabotageCardCost(final Integer cost) {
        nastySurpriseCardCost = cost;
    }

    /**
     * Sets the cost of a special movement card.
     * 
     * @param cost
     *            the cost of a special movement card
     */
    public final void setSpecialMovementCardCost(final Integer cost) {
        specialMoveCardCost = cost;
    }

    /**
     * Sets the cost of a wager.
     * 
     * @param cost
     *            the cost of a wager
     */
    public final void setWagerCost(final Integer cost) {
        wagerCost = cost;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
