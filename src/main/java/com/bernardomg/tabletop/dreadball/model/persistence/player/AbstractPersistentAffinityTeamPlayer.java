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

package com.bernardomg.tabletop.dreadball.model.persistence.player;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.bernardomg.tabletop.dreadball.model.player.AffinityTeamPlayer;
import com.bernardomg.tabletop.dreadball.model.player.stats.AffinityGroup;

/**
 * Abstract root for a player with affinity groups, and various costs which will
 * depend on how many of such affinities are shared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@MappedSuperclass
public abstract class AbstractPersistentAffinityTeamPlayer
        extends AbstractPersistentTeamPlayer implements AffinityTeamPlayer {

    /**
     * Player affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "player_affinities",
            joinColumns = { @JoinColumn(name = "player_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAffinityGroup> affinities   = new LinkedHashSet<PersistentAffinityGroup>();

    /**
     * Ally cost.
     */
    @Column(name = "cost_ally")
    private Integer                                   allyCost     = 0;

    /**
     * Friend cost.
     */
    @Column(name = "cost_friend")
    private Integer                                   friendCost   = 0;

    /**
     * Player hated affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "player_hated_affinities",
            joinColumns = { @JoinColumn(name = "player_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAffinityGroup> hated        = new LinkedHashSet<PersistentAffinityGroup>();

    /**
     * Stranger cost.
     */
    @Column(name = "cost_stranger")
    private Integer                                   strangerCost = 0;

    /**
     * Default constructor.
     */
    public AbstractPersistentAffinityTeamPlayer() {
        super();
    }

    /**
     * Adds an affinity group to the player.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void addAffinityGroup(final PersistentAffinityGroup affinity) {
        getAffinityGroupsModifiable().add(affinity);
    }

    /**
     * Adds a hated affinity group to the player.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void
            addHatedAffinityGroup(final PersistentAffinityGroup affinity) {
        getHatedAffinityGroupsModifiable().add(affinity);
    }

    @Override
    public final Collection<AffinityGroup> getAffinityGroups() {
        final Collection<AffinityGroup> col;

        col = new ArrayList<>();
        for (final AffinityGroup affinity : getAffinityGroupsModifiable()) {
            col.add(affinity);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final Integer getAllyCost() {
        return allyCost;
    }

    @Override
    public final Integer getFriendCost() {
        return friendCost;
    }

    @Override
    public final Collection<AffinityGroup> getHatedAffinityGroups() {
        final Collection<AffinityGroup> col;

        col = new ArrayList<>();
        for (final AffinityGroup affinity : getHatedAffinityGroupsModifiable()) {
            col.add(affinity);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final Integer getStrangerCost() {
        return strangerCost;
    }

    /**
     * Removes an affinity group from the player.
     * 
     * @param affinity
     *            the affinity group to remove
     */
    public final void removeAffinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    /**
     * Removes a hated affinity group from the player.
     * 
     * @param affinity
     *            the hated affinity group to remove
     */
    public final void removeHatedAffinityGroup(final AffinityGroup affinity) {
        getHatedAffinityGroupsModifiable().remove(affinity);
    }

    /**
     * Sets the player affinities.
     * <p>
     * All the affinities in the player will be removed and swapped with the
     * received ones.
     * 
     * @param affinityGroups
     *            the affinities to set on the player
     */
    public final void
            setAffinityGroups(final Collection<AffinityGroup> affinityGroups) {
        getAffinityGroupsModifiable().clear();

        if (affinityGroups != null) {
            for (final AffinityGroup affinity : affinityGroups) {
                checkArgument(affinity instanceof PersistentAffinityGroup,
                        "All the affinities should be an instanceof JPAAffinityGroup");

                getAffinityGroupsModifiable()
                        .add((PersistentAffinityGroup) affinity);
            }
        }
    }

    /**
     * Sets the player's ally cost.
     * 
     * @param cost
     *            the player's ally cost
     */
    public final void setAllyCost(final Integer cost) {
        allyCost = cost;
    }

    /**
     * Sets the player's friend cost.
     * 
     * @param cost
     *            the player's friend cost
     */
    public final void setFriendCost(final Integer cost) {
        friendCost = cost;
    }

    /**
     * Sets the player hated affinities.
     * <p>
     * All the hated affinities in the player will be removed and swapped with
     * the received ones.
     * 
     * @param affinityGroups
     *            the hated affinities to set on the player
     */
    public final void setHatedAffinityGroups(
            final Collection<AffinityGroup> affinityGroups) {
        getHatedAffinityGroupsModifiable().clear();

        if (affinityGroups != null) {
            for (final AffinityGroup affinity : affinityGroups) {
                checkArgument(affinity instanceof PersistentAffinityGroup,
                        "All the affinities should be an instanceof JPAAffinityGroup");

                getHatedAffinityGroupsModifiable()
                        .add((PersistentAffinityGroup) affinity);
            }
        }
    }

    /**
     * Sets the player's stranger cost.
     * 
     * @param cost
     *            the player's stranger cost
     */
    public final void setStrangerCost(final Integer cost) {
        strangerCost = cost;
    }

    /**
     * Returns the modifiable collection of the player's affinity groups.
     * 
     * @return the modifiable collection of the player's affinity groups
     */
    private final Collection<PersistentAffinityGroup>
            getAffinityGroupsModifiable() {
        return affinities;
    }

    /**
     * Returns the modifiable collection of the player's hated affinity groups.
     * 
     * @return the modifiable collection of the player's hated affinity groups
     */
    private final Collection<PersistentAffinityGroup>
            getHatedAffinityGroupsModifiable() {
        return hated;
    }

}
