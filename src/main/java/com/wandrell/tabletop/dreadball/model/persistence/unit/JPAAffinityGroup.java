package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;

@Entity(name = "AffinityGroup")
@Table(name = "affinity_groups")
public final class JPAAffinityGroup
        implements AffinityGroup, PersistenceEntity, Serializable {

    private static final long serialVersionUID = 3702432119601675635L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    @Column(name = "name", unique = true)
    private String            name;

    public JPAAffinityGroup() {
        super();
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

        final JPAAffinityGroup other;

        other = (JPAAffinityGroup) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final String getAffinityGroupName() {
        return name;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    public final void setAffinityGroupName(final String name) {
        this.name = name;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
