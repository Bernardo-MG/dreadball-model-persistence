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

package com.bernardomg.tabletop.dreadball.model.persistence.player.component;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAbility;
import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAttributes;
import com.bernardomg.tabletop.dreadball.model.player.Role;
import com.bernardomg.tabletop.dreadball.model.player.component.Component;
import com.bernardomg.tabletop.dreadball.model.player.component.ComponentLocation;
import com.bernardomg.tabletop.dreadball.model.player.stats.Ability;
import com.bernardomg.tabletop.dreadball.model.player.stats.Attributes;

/**
 * Abstract root for a component used for creating a composite player.
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "COMPONENT_ABILITIES",
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
    @ManyToOne
    @JoinColumn(name = "location_id")
    private PersistentComponentLocation         location;

    /**
     * Component name.
     */
    @Column(name = "name", unique = true)
    private String                              name       = "";

    /**
     * Component team roles.
     */
    @ElementCollection(targetClass = Role.class)
    @JoinTable(name = "COMPONENT_ROLES",
            joinColumns = @JoinColumn(name = "component_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private final Collection<Role>              roles      = new LinkedHashSet<Role>();

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
     * Adds a team role role.
     * 
     * @param role
     *            the team role role to add
     */
    public final void addRole(final Role role) {
        getRolesModifiable().add(role);
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
        final AbstractPersistentComponent other = (AbstractPersistentComponent) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public Collection<Ability> getAbilities() {
        final Collection<Ability> col;

        col = new ArrayList<>();
        for (final Ability ability : getAbilitiesModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Returns the ID assigned to this entity.
     * 
     * @return the entity's ID
     */
    public Integer getId() {
        return id;
    }

    @Override
    public ComponentLocation getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Role> getRoles() {
        return Collections.unmodifiableCollection(getRolesModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
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
     * Removes a team role role.
     * 
     * @param role
     *            the team role role to remove
     */
    public final void removeRole(final Role role) {
        getRolesModifiable().remove(role);
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
    public void setAbilities(final Collection<Ability> compAbilities) {
        if (compAbilities != null) {
            for (final Ability ability : compAbilities) {
                checkArgument(ability instanceof PersistentAbility,
                        "All the abilities should be an instanceof JPAAbility");

                getAbilitiesModifiable().add((PersistentAbility) ability);
            }
        }
    }

    /**
     * Sets the component attributes bonus.
     * 
     * @param attrs
     *            the attributes bonus for the component
     */
    public void setAttributes(final Attributes attrs) {
        checkArgument(attrs instanceof PersistentAttributes,
                "The Attributes should be an instanceof JPAAttributes");

        attributes = (PersistentAttributes) attrs;
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public void setId(final Integer identifier) {
        id = identifier;
    }

    /**
     * Sets the component location.
     * 
     * @param compLocation
     *            the component location
     */
    public void setLocation(final PersistentComponentLocation compLocation) {
        location = compLocation;
    }

    /**
     * Sets the component name.
     * 
     * @param componentName
     *            the component name
     */
    public void setName(final String componentName) {
        name = componentName;
    }

    /**
     * Sets the component team role roles.
     * <p>
     * All the team role roles which the component currently has will be removed
     * and swapped with the received ones.
     * 
     * @param comRoles
     *            the team role roles to set on the component
     */
    public void setRoles(final Collection<Role> comRoles) {
        getRolesModifiable().clear();

        if (comRoles != null) {
            getRolesModifiable().addAll(comRoles);
        }
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
     * Returns the modifiable collection of the component's team role roles.
     * 
     * @return the modifiable collection of the component's team role roles
     */
    private final Collection<Role> getRolesModifiable() {
        return roles;
    }

}
