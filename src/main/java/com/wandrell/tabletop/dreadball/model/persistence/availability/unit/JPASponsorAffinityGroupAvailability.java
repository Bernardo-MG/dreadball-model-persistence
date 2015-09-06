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

@Entity(name = "SponsorAffinityGroupAvailability")
@Table(name = "sponsor_affinity_avas")
public final class JPASponsorAffinityGroupAvailability implements
        SponsorAffinityGroupAvailability, PersistenceEntity, Serializable {

    private static final long                  serialVersionUID = -6796465298138862022L;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sponsor_affinity_avas_affinity_groups",
            joinColumns = { @JoinColumn(name = "sponsor_affinity_ava_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> groups           = new LinkedHashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                            id               = -1;
    @Column(name = "name", unique = true)
    private String                             name             = "";
    @Column(name = "rank_increase", unique = true)
    private Boolean                            rankIncrease     = false;

    public JPASponsorAffinityGroupAvailability() {
        super();
    }

    public final void addAfinityGroup(final AffinityGroup affinity) {
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

    public final void removeAfinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    public final void
            setAffinityGroups(final Collection<JPAAffinityGroup> groups) {
        checkNotNull(groups, "Received a null pointer as groups");

        getAffinityGroupsModifiable().clear();
        getAffinityGroupsModifiable().addAll(groups);
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setIncludingRankIncrease(final Boolean increase) {
        rankIncrease = increase;
    }

    public final void setName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return groups;
    }

}
