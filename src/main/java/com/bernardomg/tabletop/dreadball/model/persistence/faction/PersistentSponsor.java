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

package com.bernardomg.tabletop.dreadball.model.persistence.faction;

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

import com.bernardomg.tabletop.dreadball.model.faction.Sponsor;
import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAffinityGroup;
import com.bernardomg.tabletop.dreadball.model.player.stats.AffinityGroup;
import com.google.common.base.MoreObjects;

/**
 * Sponsor, which are the Dreadball Xtreme (DBX) factions.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "Sponsor")
@Table(name = "SPONSORS")
public class PersistentSponsor implements Sponsor, Serializable {

    /**
     * Serialization ID.
     */
    private static final long                  serialVersionUID = -6236019919297159189L;

    /**
     * Sponsor affinity groups.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SPONSOR_AFFINITIES",
            joinColumns = { @JoinColumn(name = "sponsor_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Set<PersistentAffinityGroup> affinities       = new HashSet<>();

    /**
     * Sponsor cash.
     */
    @Column(name = "cash")
    private Integer                            cash             = 0;

    /**
     * Sponsor's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                            id               = -1;

    /**
     * Sponsor name.
     */
    @Column(name = "name", unique = true)
    private String                             name             = "";

    /**
     * Sponsor rank.
     */
    @Column(name = "rank")
    private Integer                            rank             = 0;

    /**
     * Default constructor.
     */
    public PersistentSponsor() {
        super();
    }

    @Override
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

        final PersistentSponsor other;

        other = (PersistentSponsor) obj;
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

    @Override
    public Integer getCash() {
        return cash;
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
    public Integer getRank() {
        return rank;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final void removeAffinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    @Override
    public void
            setAffinityGroups(final Collection<AffinityGroup> affinityGroups) {
        getAffinityGroupsModifiable().clear();

        if (affinityGroups != null) {
            for (final AffinityGroup affinity : affinityGroups) {
                checkArgument(affinity instanceof PersistentAffinityGroup,
                        "The affinities should be an instance of JPAAffinityGroup");
                getAffinityGroupsModifiable()
                        .add((PersistentAffinityGroup) affinity);
            }
        }
    }

    @Override
    public void setCash(final Integer spareCash) {
        cash = spareCash;
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

    @Override
    public void setName(final String sponsorName) {
        name = sponsorName;
    }

    @Override
    public void setRank(final Integer sponsorRank) {
        rank = sponsorRank;
    }

    /**
     * Sets the sponsor name.
     * 
     * @param sponsorName
     *            the sponsor name
     */
    public void setSponsorName(final String sponsorName) {
        name = sponsorName;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    /**
     * Returns the modifiable collection of the sponsor's affinity groups.
     * 
     * @return the modifiable collection of the sponsor's affinity groups
     */
    private final Collection<PersistentAffinityGroup>
            getAffinityGroupsModifiable() {
        return affinities;
    }

}
