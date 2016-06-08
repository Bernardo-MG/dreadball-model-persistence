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

package com.wandrell.tabletop.dreadball.model.persistence.availability.unit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.availability.unit.SponsorAffinityGroupAvailability;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;

/**
 * Persistent JPA-based implementation of
 * {@link SponsorAffinityGroupAvailability}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "SponsorAffinityGroupAvailability")
@Table(name = "sponsor_affinity_avas")
public final class JPASponsorAffinityGroupAvailability implements
        SponsorAffinityGroupAvailability, PersistenceEntity, Serializable {

    /**
     * Serialization ID.
     */
    private static final long                  serialVersionUID = -6796465298138862022L;

    /**
     * Available affinity groups.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sponsor_affinity_avas_affinity_groups",
            joinColumns = { @JoinColumn(name = "sponsor_affinity_ava_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> affinities       = new LinkedHashSet<>();

    /**
     * Availability's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                            id               = -1;

    /**
     * Availability's name.
     */
    @Column(name = "name", unique = true)
    private String                             name             = "";

    /**
     * Flag indicating if the availability allows increasing the rank.
     */
    @Column(name = "rank_increase", unique = true)
    private Boolean                            rankIncrease     = false;

    /**
     * Constructs a {@code JPATeamType}.
     */
    public JPASponsorAffinityGroupAvailability() {
        super();
    }

    /**
     * Adds an affinity group.
     * 
     * @param affinity
     *            the affinity group to add
     */
    public final void addAffinityGroup(final AffinityGroup affinity) {
        checkArgument(affinity instanceof JPAAffinityGroup,
                "The AffinityGroup should be an instance of JPAAffinityGroup");

        getAffinityGroupsModifiable().add((JPAAffinityGroup) affinity);
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

        final JPASponsorAffinityGroupAvailability other;

        other = (JPASponsorAffinityGroupAvailability) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Collection<AffinityGroup> getAffinityGroups() {
        final Collection<AffinityGroup> result;

        result = new LinkedList<>();
        for (final AffinityGroup group : getAffinityGroupsModifiable()) {
            result.add(group);
        }

        return result;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final Boolean isIncludingRankIncrease() {
        return rankIncrease;
    }

    /**
     * Removes an affinity group.
     * 
     * @param affinity
     *            the affinity group to remove
     */
    public final void removeAffinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    /**
     * Sets the availability affinity groups.
     * <p>
     * All the affinity groups which the availability currently has will be
     * removed and swapped with the received ones.
     * 
     * @param affinityGroups
     *            the affinity groups to set on the unit
     */
    public final void setAffinityGroups(
            final Collection<JPAAffinityGroup> affinityGroups) {
        checkNotNull(affinityGroups, "Received a null pointer as groups");

        getAffinityGroupsModifiable().clear();
        getAffinityGroupsModifiable().addAll(affinityGroups);
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

    /**
     * Sets the flag indicating if the availability allows increasing the rank.
     * 
     * @param increase
     *            flag indicating if the availability allows increasing the rank
     */
    public final void setIncludingRankIncrease(final Boolean increase) {
        rankIncrease = increase;
    }

    /**
     * Sets the availability's name.
     * 
     * @param nameAva
     *            the availability's name
     */
    public final void setName(final String nameAva) {
        checkNotNull(nameAva, "Received a null pointer as name");

        name = nameAva;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

    /**
     * Returns the modifiable collection of the availability's affinity groups.
     * 
     * @return the modifiable collection of the availability's affinity groups
     */
    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return affinities;
    }

}
