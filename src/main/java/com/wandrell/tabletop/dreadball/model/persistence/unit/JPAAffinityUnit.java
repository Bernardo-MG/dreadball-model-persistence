package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;

@Entity(name = "AffinityUnit")
@Table(name = "affinity_units")
public final class JPAAffinityUnit extends AbstractJPAUnit
        implements AffinityUnit, PersistenceEntity, Serializable {

    private static final long                  serialVersionUID = -6317901977987115397L;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> affinities       = new LinkedHashSet<JPAAffinityGroup>();
    @Column(name = "cost_ally")
    private Integer                            costAlly         = 0;
    @Column(name = "cost_friend")
    private Integer                            costFriend       = 0;
    @Column(name = "cost_stranger")
    private Integer                            costStranger     = 0;

    public JPAAffinityUnit() {
        super();
    }

    public final void addAffinityGroup(final JPAAffinityGroup affinity) {
        getAffinityGroupsModifiable().add(affinity);
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
    public final Integer getStrangerCost() {
        return costStranger;
    }

    public final void removeAffinityGroup(final AffinityGroup affinity) {
        checkNotNull(affinity, "Received a null pointer as ally affinity");

        getAffinityGroupsModifiable().remove(affinity);
    }

    public final void setAllyCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as ally cost");

        costAlly = cost;
    }

    public final void setFriendCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as friend cost");

        costFriend = cost;
    }

    public final void setStrangerCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as stranger cost");

        costStranger = cost;
    }

    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return affinities;
    }

}
