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

package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;

/**
 * Abstract persistent JPA-based implementation of {@link AffinityUnit}.
 * 
 * @author Bernardo Martínez Garrido
 */
@MappedSuperclass
public abstract class AbstractJPAAffinityUnit extends AbstractJPAUnit
        implements AffinityUnit {

    /**
     * Unit affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> affinities   = new LinkedHashSet<JPAAffinityGroup>();

    /**
     * Ally cost.
     */
    @Column(name = "cost_ally")
    private Integer                            costAlly     = 0;

    /**
     * Friend cost.
     */
    @Column(name = "cost_friend")
    private Integer                            costFriend   = 0;

    /**
     * Stranger cost.
     */
    @Column(name = "cost_stranger")
    private Integer                            costStranger = 0;

    /**
     * Unit hated affinities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_hated_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> hated        = new LinkedHashSet<JPAAffinityGroup>();

    /**
     * Unit name.
     */
    @Column(name = "name")
    private String                             name         = "";

    /**
     * Constructs a {@code AbstractJPAAffinityUnit}.
     */
    public AbstractJPAAffinityUnit() {
        super();
    }

    /**
     * Adds an affinity group to the unit.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void addAffinityGroup(final JPAAffinityGroup affinity) {
        checkNotNull(affinity, "Received a null pointer as the affinity group");

        getAffinityGroupsModifiable().add(affinity);
    }

    /**
     * Adds a hated affinity group to the unit.
     * 
     * @param affinity
     *            affinity group to add
     */
    public final void addHatedAffinityGroup(final JPAAffinityGroup affinity) {
        checkNotNull(affinity,
                "Received a null pointer as the hated affinity group");

        getHatedAffinityGroupsModifiable().add(affinity);
    }

    @Override
    public final Collection<AffinityGroup> getAffinityGroups() {
        final Collection<AffinityGroup> col;

        col = new LinkedList<>();
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
    public final Integer getCost() {
        return 0;
    }

    @Override
    public final Integer getFriendCost() {
        return costFriend;
    }

    @Override
    public final Collection<AffinityGroup> getHatedAffinityGroups() {
        final Collection<AffinityGroup> col;

        col = new LinkedList<>();
        for (final AffinityGroup affinity : getHatedAffinityGroupsModifiable()) {
            col.add(affinity);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final String getName() {
        return name;
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
            checkArgument(affinity instanceof JPAAffinityGroup,
                    "All the affinities should be an instanceof JPAAffinityGroup");

            getAffinityGroupsModifiable().add((JPAAffinityGroup) affinity);
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
            checkArgument(affinity instanceof JPAAffinityGroup,
                    "All the affinities should be an instanceof JPAAffinityGroup");

            getHatedAffinityGroupsModifiable().add((JPAAffinityGroup) affinity);
        }
    }

    @Override
    public final void setName(final String unitName) {
        name = unitName;
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
    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return affinities;
    }

    /**
     * Returns the modifiable collection of the unit's hated affinity groups.
     * 
     * @return the modifiable collection of the unit's hated affinity groups
     */
    private final Collection<JPAAffinityGroup>
            getHatedAffinityGroupsModifiable() {
        return hated;
    }

}
