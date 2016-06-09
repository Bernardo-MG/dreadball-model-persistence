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

package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkArgument;
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
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAttributes;
import com.wandrell.tabletop.dreadball.model.unit.Role;
import com.wandrell.tabletop.dreadball.model.unit.Unit;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.Attributes;

/**
 * Abstract persistent JPA-based implementation of {@link Unit}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractJPAUnit implements Unit {

    /**
     * Unit abilities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_abilities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<JPAAbility> abilities    = new LinkedHashSet<>();

    /**
     * Unit attributes.
     */
    @Embedded
    private JPAAttributes                attributes   = new JPAAttributes();

    /**
     * Flag indicating if the unit is a giant.
     */
    @Column(name = "giant")
    private Boolean                      giant        = false;

    /**
     * Unit's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                      id           = -1;

    /**
     * Unit team position.
     */
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Role                         position     = Role.JACK;

    /**
     * Unit template name.
     */
    @Column(name = "template_name", unique = true)
    private String                       templateName = "";

    /**
     * Constructs a {@code AbstractJPAUnit}.
     */
    public AbstractJPAUnit() {
        super();
    }

    /**
     * Adds an ability to the unit.
     * 
     * @param ability
     *            the ability to add
     */
    public final void addAbility(final Ability ability) {
        checkNotNull(ability, "Received a null pointer as ability");
        checkArgument(ability instanceof JPAAbility,
                "The Ability should be an instanceof JPAAbility");

        getAbilitiesModifiable().add((JPAAbility) ability);
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
        return Objects.equals(id, other.id);
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
     * Returns the ID assigned to this unit.
     * 
     * @return the entity's ID
     */
    public final Integer getId() {
        return id;
    }

    @Override
    public final Role getRole() {
        return position;
    }

    @Override
    public final String getTemplateName() {
        return templateName;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public final Boolean isGiant() {
        return giant;
    }

    /**
     * Removes an ability from the unit.
     * 
     * @param ability
     *            the ability to remove
     */
    public final void removeAbility(final Ability ability) {
        getAbilitiesModifiable().remove(ability);
    }

    /**
     * Sets the unit abilities.
     * <p>
     * All the abilities which the unit currently has will be removed and
     * swapped with the received ones.
     * 
     * @param unitAbilities
     *            the abilities to set on the unit
     */
    public final void setAbilities(final Collection<Ability> unitAbilities) {
        checkNotNull(unitAbilities, "Received a null pointer as abilities");

        getAbilitiesModifiable().clear();

        for (final Ability ability : unitAbilities) {
            checkArgument(ability instanceof JPAAbility,
                    "All the abilities should be an instanceof JPAAbility");

            getAbilitiesModifiable().add((JPAAbility) ability);
        }
    }

    /**
     * Sets the unit attributes.
     * 
     * @param attrs
     *            the attributes for the unit
     */
    public final void setAttributes(final Attributes attrs) {
        checkNotNull(attrs, "Received a null pointer as attributes");
        checkArgument(attrs instanceof JPAAttributes,
                "The Attributes should be an instanceof JPAAttributes");

        attributes = (JPAAttributes) attrs;
    }

    /**
     * Sets the unit giant flag.
     * 
     * @param giantFlag
     *            the flag indicating if the unit is a giant
     */
    public final void setGiant(final Boolean giantFlag) {
        checkNotNull(giantFlag, "Received a null pointer as giant flag");

        giant = giantFlag;
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
     * Sets the unit team position.
     * 
     * @param pos
     *            the team position for the unit
     */
    public final void setPosition(final Role pos) {
        checkNotNull(pos, "Received a null pointer as team position role");

        position = pos;
    }

    /**
     * Sets the unit's template name.
     * 
     * @param name
     *            the template name
     */
    public final void setTemplateName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        templateName = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", templateName)
                .add("id", id).toString();
    }

    /**
     * Returns the modifiable collection of the unit's abilities.
     * 
     * @return the modifiable collection of the unit's abilities
     */
    private final Collection<JPAAbility> getAbilitiesModifiable() {
        return abilities;
    }

}
