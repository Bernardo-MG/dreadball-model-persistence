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
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.faction.Sponsor;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;

@Entity(name = "Sponsor")
@Table(name = "sponsors")
public final class JPASponsor
        implements Sponsor, PersistenceEntity, Serializable {

    private static final long                  serialVersionUID = -6236019919297159189L;
    @Column(name = "cash")
    private Integer                            cash;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sponsor_affinity_groups",
            joinColumns = { @JoinColumn(name = "sponsor_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> groups           = new LinkedHashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                            id               = -1;
    @Column(name = "name", unique = true)
    private String                             name;
    @Column(name = "rank")
    private Integer                            rank;

    public JPASponsor() {
        super();
    }

    @Override
    public final void addAfinityGroup(final AffinityGroup affinity) {
        checkArgument(affinity instanceof JPAAffinityGroup,
                "The AffinityGroup should be an instance of JPAAffinityGroup");

        getAffinityGroupsModifiable().add((JPAAffinityGroup) affinity);
    }

    public final void addPlayerGroup(final JPAAffinityGroup group) {
        checkNotNull(group, "Received a null pointer as group");

        this.groups.add(group);
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

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getRank() {
        return rank;
    }

    @Override
    public final String getSponsorName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final void removeAfinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    public final void removePlayerGroup(final JPAAffinityGroup group) {
        checkNotNull(group, "Received a null pointer as group");

        this.groups.remove(group);
    }

    @Override
    public final void setCash(final Integer cash) {
        this.cash = cash;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setNameToken(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    public final void
            setPlayerGroups(final Collection<JPAAffinityGroup> groups) {
        checkNotNull(groups, "Received a null pointer as groups");

        this.groups.clear();
        this.groups.addAll(groups);
    }

    @Override
    public final void setRank(final Integer rank) {
        checkNotNull(rank, "Received a null pointer as rank");

        this.rank = rank;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return groups;
    }

}
