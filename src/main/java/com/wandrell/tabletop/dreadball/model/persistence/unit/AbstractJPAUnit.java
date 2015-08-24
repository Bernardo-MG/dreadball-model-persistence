package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkNotNull;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAttributesHolder;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.dreadball.model.unit.Unit;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.AttributesHolder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractJPAUnit implements Unit, PersistenceEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_abilities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAbility> abilities  = new LinkedHashSet<>();
    @Embedded
    private final JPAAttributesHolder    attributes = new JPAAttributesHolder();
    @Column(name = "giant")
    private Boolean                      giant;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                      id         = -1;
    @Column(name = "name", unique = true)
    private String                       name;
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private TeamPosition                 position;

    public AbstractJPAUnit() {
        super();
    }

    public final void addAbility(final JPAAbility ability) {
        getAbilitiesModifiable().add(ability);
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

        final AbstractJPAUnit other;

        other = (AbstractJPAUnit) obj;
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
    public final AttributesHolder getAttributes() {
        return attributes;
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

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final Collection<JPAAbility> getAbilitiesModifiable() {
        return abilities;
    }

}
