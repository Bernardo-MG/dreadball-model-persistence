package com.wandrell.tabletop.dreadball.model.persistence.unit.component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.ManyToOne;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAttributesHolder;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.dreadball.model.unit.component.ComponentLocation;
import com.wandrell.tabletop.dreadball.model.unit.component.UnitComponent;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.AttributesHolder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractJPAUnitComponent
        implements UnitComponent, PersistenceEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "component_abilities",
            joinColumns = { @JoinColumn(name = "component_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAbility> abilities  = new LinkedHashSet<>();
    @Embedded
    private JPAAttributesHolder          attributes = new JPAAttributesHolder();
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                      id         = -1;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private JPAComponentLocation         location;
    @Column(name = "name", unique = true)
    private String                       name;
    @ElementCollection(targetClass = TeamPosition.class)
    @JoinTable(name = "component_positions",
            joinColumns = @JoinColumn(name = "component_id") )
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    final Collection<TeamPosition>       positions  = new LinkedList<TeamPosition>();

    public AbstractJPAUnitComponent() {
        super();
    }

    public final void addAbility(final Ability ability) {
        checkArgument(ability instanceof JPAAbility,
                "The Ability should be an instanceof JPAAbility");

        getAbilitiesModifiable().add((JPAAbility) ability);
    }

    public final void addTeamPosition(final TeamPosition position) {
        getTeamPositionsModifiable().add(position);
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
    public final String getComponentName() {
        return name;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final ComponentLocation getLocation() {
        return location;
    }

    @Override
    public final Collection<TeamPosition> getTeamPositions() {
        return Collections.unmodifiableCollection(getTeamPositionsModifiable());
    }

    public final void removeAbility(final Ability ability) {
        getAbilitiesModifiable().remove(ability);
    }

    public final void removeTeamPosition(final TeamPosition position) {
        getTeamPositionsModifiable().remove(position);
    }

    public final void setAbilities(final Collection<Ability> abilities) {
        checkNotNull(abilities, "Received a null pointer as abilities");

        for (final Ability ability : abilities) {
            checkArgument(ability instanceof JPAAbility,
                    "All the abilities should be an instanceof JPAAbility");

            getAbilitiesModifiable().add((JPAAbility) ability);
        }
    }

    public final void setAttributes(final AttributesHolder attributes) {
        checkNotNull(attributes, "Received a null pointer as attributes");
        checkArgument(attributes instanceof JPAAttributesHolder,
                "The AttributesHolder should be an instanceof JPAAttributesHolder");

        this.attributes = (JPAAttributesHolder) attributes;
    }

    public final void setComponentName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

    public final void setLocation(final JPAComponentLocation location) {
        checkNotNull(location, "Received a null pointer as location");

        this.location = location;
    }

    public final void
            setTeamPosition(final Collection<TeamPosition> positions) {
        checkNotNull(positions,
                "Received a null pointer as tema position roles");

        getTeamPositionsModifiable().clear();
        getTeamPositionsModifiable().addAll(positions);
    }

    private final Collection<JPAAbility> getAbilitiesModifiable() {
        return abilities;
    }

    private final Collection<TeamPosition> getTeamPositionsModifiable() {
        return positions;
    }

}
