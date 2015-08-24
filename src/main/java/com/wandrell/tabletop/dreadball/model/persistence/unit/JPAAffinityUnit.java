package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAttributesHolder;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.AttributesHolder;

@Entity(name = "AffinityUnit")
@Table(name = "affinity_units")
public final class JPAAffinityUnit
        implements AffinityUnit, PersistenceEntity, Serializable {

    private static final long                  serialVersionUID = -6317901977987115397L;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_abilities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAbility>       abilities        = new LinkedHashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_affinities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "affinity_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAffinityGroup> affinities       = new LinkedList<JPAAffinityGroup>();
    @Embedded
    private final JPAAttributesHolder          attributes       = new JPAAttributesHolder();
    @Column(name = "cost")
    private Integer                            cost;
    @Column(name = "cost_ally")
    private Integer                            costAlly;
    @Column(name = "cost_friend")
    private Integer                            costFriend;
    @Column(name = "cost_stranger")
    private Integer                            costStranger;
    @Column(name = "giant")
    private Boolean                            giant;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                            id               = -1;
    @Column(name = "name", unique = true)
    private String                             name;
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private TeamPosition                       position;

    public JPAAffinityUnit() {
        super();
    }

    public final void addAbility(final JPAAbility ability) {
        getAbilitiesModifiable().add(ability);
    }

    public final void addAffinityGroup(final JPAAffinityGroup affinity) {
        getAffinityGroupsModifiable().add(affinity);
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

        final JPAAffinityUnit other;

        other = (JPAAffinityUnit) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Collection<Ability> getAbilities() {
        final Collection<Ability> col;

        col = new LinkedList<>();
        for (final Ability ability : getAbilitiesModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
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
    public final AttributesHolder getAttributes() {
        return attributes;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getFriendCost() {
        return costFriend;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final TeamPosition getPosition() {
        return position;
    }

    @Override
    public final Integer getStrangerCost() {
        return costStranger;
    }

    @Override
    public final String getTemplateName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final Boolean isGiant() {
        return giant;
    }

    public final void removeAbility(final Ability ability) {
        getAbilitiesModifiable().remove(ability);
    }

    public final void removeAffinityGroup(final AffinityGroup affinity) {
        getAffinityGroupsModifiable().remove(affinity);
    }

    public final void setAllyCost(final Integer cost) {
        costAlly = cost;
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    public final void setFriendCost(final Integer cost) {
        costFriend = cost;
    }

    public final void setGiant(final Boolean giant) {
        this.giant = giant;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setPosition(final TeamPosition position) {
        this.position = position;
    }

    public final void setStrangerCost(final Integer cost) {
        costStranger = cost;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final Collection<JPAAbility> getAbilitiesModifiable() {
        return abilities;
    }

    private final Collection<JPAAffinityGroup> getAffinityGroupsModifiable() {
        return affinities;
    }

}
