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

package com.wandrell.tabletop.dreadball.model.persistence.faction;

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
import com.wandrell.tabletop.dreadball.model.faction.Sponsor;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;

/**
 * Persistent JPA-based implementation of {@link Sponsor}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "Sponsor")
@Table(name = "sponsors")
public final class JPASponsor implements Sponsor, Serializable {

    /**
     * Serialization ID.
     */
    private static final long                  serialVersionUID = -6236019919297159189L;

    /**
     * Sponsor affinity groups.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sponsor_affinity_groups",
            joinColumns = { @JoinColumn(name = "sponsor_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> affinities       = new LinkedHashSet<>();

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
     * Constructs a {@code JPASponsor}.
     */
    public JPASponsor() {
        super();
    }

    @Override
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

        final JPASponsor other;

        other = (JPASponsor) obj;
        return Objects.equals(name, other.name);
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
    public final Integer getCash() {
        return cash;
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
    public final String getName() {
        return name;
    }

    @Override
    public final Integer getRank() {
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
    public final void
            setAffinityGroups(final Collection<AffinityGroup> affinityGroups) {
        checkNotNull(affinityGroups, "Received a null pointer as groups");

        getAffinityGroupsModifiable().clear();

        for (final AffinityGroup affinity : affinityGroups) {
            checkArgument(affinity instanceof JPAAffinityGroup,
                    "The affinities should be an instance of JPAAffinityGroup");
            getAffinityGroupsModifiable().add((JPAAffinityGroup) affinity);
        }
    }

    @Override
    public final void setCash(final Integer spareCash) {
        checkNotNull(spareCash, "Received a null pointer as cash");

        cash = spareCash;
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

    @Override
    public final void setName(final String sponsorName) {
        name = sponsorName;
    }

    @Override
    public final void setRank(final Integer sponsorRank) {
        checkNotNull(sponsorRank, "Received a null pointer as rank");

        rank = sponsorRank;
    }

    /**
     * Sets the sponsor name.
     * 
     * @param sponsorName
     *            the sponsor name
     */
    public final void setSponsorName(final String sponsorName) {
        checkNotNull(sponsorName, "Received a null pointer as name");

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
    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return affinities;
    }

}
