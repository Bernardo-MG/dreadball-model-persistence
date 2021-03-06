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

package com.bernardomg.tabletop.dreadball.model.persistence.availability.affinity;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.availability.affinity.SponsorAffinityGroupAvailability;
import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAffinityGroup;
import com.bernardomg.tabletop.dreadball.model.player.stats.AffinityGroup;
import com.google.common.base.MoreObjects;

/**
 * Affinity group availabilities for a
 * {@link com.bernardomg.tabletop.dreadball.model.faction.Sponsor Sponsors}, to
 * be acquired during their creation.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "SponsorAffinityGroupAvailability")
@Table(name = "AFFINITY_SETS")
public class PersistentSponsorAffinityGroupAvailability
        implements SponsorAffinityGroupAvailability, Serializable {

    /**
     * Serialization ID.
     */
    private static final long                  serialVersionUID = -6796465298138862022L;

    /**
     * Available affinity groups.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "AFFINITY_OPTIONS",
            joinColumns = { @JoinColumn(name = "affinity_set_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Set<PersistentAffinityGroup> affinities       = new HashSet<>();

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
     * Default constructor.
     */
    public PersistentSponsorAffinityGroupAvailability() {
        super();
    }

    /**
     * Adds an affinity group.
     * 
     * @param affinity
     *            the affinity group to add
     */
    public final void addAffinityGroup(final AffinityGroup affinity) {
        checkArgument(affinity instanceof PersistentAffinityGroup,
                "The AffinityGroup should be an instance of JPAAffinityGroup");

        getAffinityGroupsModifiable().add((PersistentAffinityGroup) affinity);
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

        final PersistentSponsorAffinityGroupAvailability other;

        other = (PersistentSponsorAffinityGroupAvailability) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public Collection<AffinityGroup> getAffinityGroups() {
        final Collection<AffinityGroup> result;

        result = new ArrayList<>();
        for (final AffinityGroup group : getAffinityGroupsModifiable()) {
            result.add(group);
        }

        return result;
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
    public String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Boolean isIncludingRankIncrease() {
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
     *            the affinity groups to set on the player
     */
    public void setAffinityGroups(
            final Collection<PersistentAffinityGroup> affinityGroups) {
        getAffinityGroupsModifiable().clear();

        if (affinityGroups != null) {
            getAffinityGroupsModifiable().addAll(affinityGroups);
        }
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
     * Sets the flag indicating if the availability allows increasing the rank.
     * 
     * @param increase
     *            flag indicating if the availability allows increasing the rank
     */
    public void setIncludingRankIncrease(final Boolean increase) {
        rankIncrease = increase;
    }

    /**
     * Sets the availability's name.
     * 
     * @param nameAva
     *            the availability's name
     */
    public void setName(final String nameAva) {
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
    private final Collection<PersistentAffinityGroup>
            getAffinityGroupsModifiable() {
        return affinities;
    }

}
