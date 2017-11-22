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

package com.bernardomg.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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

import com.bernardomg.tabletop.dreadball.model.unit.AffinityGroup;
import com.bernardomg.tabletop.dreadball.model.unit.AffinityUnit;

/**
 * Abstract root for a unit with affinity groups, and various costs which will
 * depend on how many of such affinities are shared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@MappedSuperclass
public abstract class AbstractPersistentAffinityUnit
        extends AbstractPersistentUnit implements AffinityUnit {

    /**
     * Unit affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAffinityGroup> affinities   = new LinkedHashSet<PersistentAffinityGroup>();

    /**
     * Ally cost.
     */
    @Column(name = "cost_ally")
    private Integer                                   costAlly     = 0;

    /**
     * Friend cost.
     */
    @Column(name = "cost_friend")
    private Integer                                   costFriend   = 0;

    /**
     * Stranger cost.
     */
    @Column(name = "cost_stranger")
    private Integer                                   costStranger = 0;

    /**
     * Unit hated affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_hated_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAffinityGroup> hated        = new LinkedHashSet<PersistentAffinityGroup>();

    /**
     * Default constructor.
     */
    public AbstractPersistentAffinityUnit() {
        super();
    }

    /**
     * Adds an affinity group to the unit.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void addAffinityGroup(final PersistentAffinityGroup affinity) {
        checkNotNull(affinity, "Received a null pointer as the affinity group");

        getAffinityGroupsModifiable().add(affinity);
    }

    /**
     * Adds a hated affinity group to the unit.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void
            addHatedAffinityGroup(final PersistentAffinityGroup affinity) {
        checkNotNull(affinity,
                "Received a null pointer as the hated affinity group");

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
        return costAlly;
    }

    @Override
    public final Integer getFriendCost() {
        return costFriend;
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
        return costStranger;
    }

    /**
     * Removes an affinity group from the unit.
     * 
     * @param affinity
     *            the affinity group to remove
     */
    public final void removeAffinityGroup(final AffinityGroup affinity) {
        checkNotNull(affinity, "Received a null pointer as the affinity group");

        getAffinityGroupsModifiable().remove(affinity);
    }

    /**
     * Removes a hated affinity group from the unit.
     * 
     * @param affinity
     *            the hated affinity group to remove
     */
    public final void removeHatedAffinityGroup(final AffinityGroup affinity) {
        checkNotNull(affinity,
                "Received a null pointer as the hated affinity group");

        getHatedAffinityGroupsModifiable().remove(affinity);
    }

    /**
     * Sets the unit affinities.
     * <p>
     * All the affinities in the unit will be removed and swapped with the
     * received ones.
     * 
     * @param affinityGroups
     *            the affinities to set on the unit
     */
    public final void
            setAffinityGroups(final Collection<AffinityGroup> affinityGroups) {
        checkNotNull(affinityGroups, "Received a null pointer as affinities");

        getAffinityGroupsModifiable().clear();

        for (final AffinityGroup affinity : affinityGroups) {
            checkArgument(affinity instanceof PersistentAffinityGroup,
                    "All the affinities should be an instanceof JPAAffinityGroup");

            getAffinityGroupsModifiable()
                    .add((PersistentAffinityGroup) affinity);
        }
    }

    /**
     * Sets the unit's ally cost.
     * 
     * @param cost
     *            the unit's ally cost
     */
    public final void setAllyCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as ally cost");

        costAlly = cost;
    }

    /**
     * Sets the unit's friend cost.
     * 
     * @param cost
     *            the unit's friend cost
     */
    public final void setFriendCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as friend cost");

        costFriend = cost;
    }

    /**
     * Sets the unit hated affinities.
     * <p>
     * All the hated affinities in the unit will be removed and swapped with the
     * received ones.
     * 
     * @param affinityGroups
     *            the hated affinities to set on the unit
     */
    public final void setHatedAffinityGroups(
            final Collection<AffinityGroup> affinityGroups) {
        checkNotNull(affinityGroups,
                "Received a null pointer as hated affinities");

        getHatedAffinityGroupsModifiable().clear();

        for (final AffinityGroup affinity : affinityGroups) {
            checkArgument(affinity instanceof PersistentAffinityGroup,
                    "All the affinities should be an instanceof JPAAffinityGroup");

            getHatedAffinityGroupsModifiable()
                    .add((PersistentAffinityGroup) affinity);
        }
    }

    /**
     * Sets the unit's stranger cost.
     * 
     * @param cost
     *            the unit's stranger cost
     */
    public final void setStrangerCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as stranger cost");

        costStranger = cost;
    }

    /**
     * Returns the modifiable collection of the unit's affinity groups.
     * 
     * @return the modifiable collection of the unit's affinity groups
     */
    private final Collection<PersistentAffinityGroup>
            getAffinityGroupsModifiable() {
        return affinities;
    }

    /**
     * Returns the modifiable collection of the unit's hated affinity groups.
     * 
     * @return the modifiable collection of the unit's hated affinity groups
     */
    private final Collection<PersistentAffinityGroup>
            getHatedAffinityGroupsModifiable() {
        return hated;
    }

}
