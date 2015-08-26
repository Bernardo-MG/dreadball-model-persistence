package com.wandrell.tabletop.dreadball.model.persistence.unit.stats;

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
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

@Entity(name = "Ability")
@Table(name = "abilities")
public final class JPAAbility
        implements Ability, PersistenceEntity, Serializable {

    private static final long serialVersionUID = 1853450793663114595L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    @Column(name = "name", unique = true)
    private String            name             = "";

    public JPAAbility() {
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

        final JPAAbility other;

        other = (JPAAbility) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final String getAbilityName() {
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

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
