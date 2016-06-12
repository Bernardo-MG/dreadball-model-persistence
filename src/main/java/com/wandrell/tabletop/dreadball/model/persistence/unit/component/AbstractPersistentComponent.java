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

import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.PersistentAbility;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.PersistentAttributes;
import com.wandrell.tabletop.dreadball.model.unit.Role;
import com.wandrell.tabletop.dreadball.model.unit.component.Component;
import com.wandrell.tabletop.dreadball.model.unit.component.ComponentLocation;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.Attributes;

/**
 * Abstract root for a component used for creating a composite unit.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractPersistentComponent implements Component {

    /**
     * Component abilities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "component_abilities",
            joinColumns = { @JoinColumn(name = "component_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAbility> abilities  = new LinkedHashSet<>();

    /**
     * Component attributes bonus.
     */
    @Embedded
    private PersistentAttributes                attributes = new PersistentAttributes();

    /**
     * Component primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                             id         = -1;

    /**
     * Component location.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private PersistentComponentLocation         location;

    /**
     * Component name.
     */
    @Column(name = "name", unique = true)
    private String                              name       = "";

    /**
     * Component team positions.
     */
    @ElementCollection(targetClass = Role.class)
    @JoinTable(name = "component_positions",
            joinColumns = @JoinColumn(name = "component_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private final Collection<Role>              positions  = new LinkedHashSet<Role>();

    /**
     * Default constructor.
     */
    public AbstractPersistentComponent() {
        super();
    }

    /**
     * Adds an ability.
     * 
     * @param ability
     *            the ability to add
     */
    public final void addAbility(final Ability ability) {
        checkArgument(ability instanceof PersistentAbility,
                "The Ability should be an instanceof JPAAbility");

        getAbilitiesModifiable().add((PersistentAbility) ability);
    }

    /**
     * Adds a team position role.
     * 
     * @param position
     *            the team position role to add
     */
    public final void addRole(final Role position) {
        getRolesModifiable().add(position);
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
    public final Attributes getAttributes() {
        return attributes;
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
    public final ComponentLocation getLocation() {
        return location;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<Role> getRoles() {
        return Collections.unmodifiableCollection(getRolesModifiable());
    }

    /**
     * Removes an ability.
     * 
     * @param ability
     *            the ability to remove
     */
    public final void removeAbility(final Ability ability) {
        getAbilitiesModifiable().remove(ability);
    }

    /**
     * Removes a team position role.
     * 
     * @param position
     *            the team position role to remove
     */
    public final void removeRole(final Role position) {
        getRolesModifiable().remove(position);
    }

    /**
     * Sets the component abilities.
     * <p>
     * All the abilities which the component currently has will be removed and
     * swapped with the received ones.
     * 
     * @param compAbilities
     *            the abilities to set on the component
     */
    public final void setAbilities(final Collection<Ability> compAbilities) {
        checkNotNull(compAbilities, "Received a null pointer as abilities");

        for (final Ability ability : compAbilities) {
            checkArgument(ability instanceof PersistentAbility,
                    "All the abilities should be an instanceof JPAAbility");

            getAbilitiesModifiable().add((PersistentAbility) ability);
        }
    }

    /**
     * Sets the component attributes bonus.
     * 
     * @param attrs
     *            the attributes bonus for the component
     */
    public final void setAttributes(final Attributes attrs) {
        checkNotNull(attrs, "Received a null pointer as attributes");
        checkArgument(attrs instanceof PersistentAttributes,
                "The Attributes should be an instanceof JPAAttributes");

        this.attributes = (PersistentAttributes) attrs;
    }

    /**
     * Sets the component name.
     * 
     * @param componentName
     *            the component name
     */
    public final void setComponentName(final String componentName) {
        checkNotNull(componentName, "Received a null pointer as name");

        name = componentName;
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

    /**
     * Sets the component location.
     * 
     * @param compLocation
     *            the component location
     */
    public final void
            setLocation(final PersistentComponentLocation compLocation) {
        checkNotNull(compLocation,
                "Received a null pointer as component location");

        location = compLocation;
    }

    /**
     * Sets the component team position roles.
     * <p>
     * All the team position roles which the component currently has will be
     * removed and swapped with the received ones.
     * 
     * @param comPositions
     *            the team position roles to set on the component
     */
    public final void setRole(final Collection<Role> comPositions) {
        checkNotNull(comPositions,
                "Received a null pointer as team position roles");

        getRolesModifiable().clear();
        getRolesModifiable().addAll(comPositions);
    }

    /**
     * Returns the modifiable collection of the component's abilities.
     * 
     * @return the modifiable collection of the component's abilities
     */
    private final Collection<PersistentAbility> getAbilitiesModifiable() {
        return abilities;
    }

    /**
     * Returns the modifiable collection of the component's team position roles.
     * 
     * @return the modifiable collection of the component's team position roles
     */
    private final Collection<Role> getRolesModifiable() {
        return positions;
    }

}