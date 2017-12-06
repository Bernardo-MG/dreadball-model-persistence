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

package com.bernardomg.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.persistence.unit.stats.PersistentAbility;
import com.bernardomg.tabletop.dreadball.model.persistence.unit.stats.PersistentAttributes;
import com.bernardomg.tabletop.dreadball.model.unit.Role;
import com.bernardomg.tabletop.dreadball.model.unit.Unit;
import com.bernardomg.tabletop.dreadball.model.unit.stats.Ability;
import com.bernardomg.tabletop.dreadball.model.unit.stats.Attributes;
import com.google.common.base.MoreObjects;

/**
 * Abstract root for the basic features all the Dreadball units have, no matter
 * if they come from Dreadball Original (DBO) or Dreadball Xtreme (DBX).
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "Unit")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "unit_type")
@Table(name = "units")
public abstract class AbstractPersistentUnit implements Unit {

    /**
     * Unit abilities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_abilities",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAbility> abilities    = new LinkedHashSet<>();

    /**
     * Unit attributes.
     */
    @Embedded
    private PersistentAttributes                attributes   = new PersistentAttributes();

    /**
     * Unit cost.
     */
    @Column(name = "cost")
    private Integer                             cost         = 0;

    /**
     * Flag indicating if the unit is a giant.
     */
    @Column(name = "giant")
    private Boolean                             giant        = false;

    /**
     * Unit's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                             id           = -1;

    /**
     * Flag indicating if the unit is a MVP.
     */
    @Column(name = "mvp")
    private final Boolean                       mvp          = false;

    /**
     * Unit name.
     */
    @Column(name = "name")
    private String                              name         = "";

    /**
     * Unit team position.
     */
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Role                                position     = Role.JACK;

    /**
     * Unit template name.
     */
    @Column(name = "template_name", unique = true)
    private String                              templateName = "";

    /**
     * Default constructor.
     */
    public AbstractPersistentUnit() {
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
        checkArgument(ability instanceof PersistentAbility,
                "The Ability should be an instanceof JPAAbility");

        getAbilitiesModifiable().add((PersistentAbility) ability);
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

        final AbstractPersistentUnit other;

        other = (AbstractPersistentUnit) obj;
        return Objects.equals(templateName, other.templateName)
                && Objects.equals(name, other.name);
    }

    @Override
    public final Collection<Ability> getAbilities() {
        final Collection<Ability> col;

        col = new ArrayList<>();
        for (final Ability ability : getAbilitiesModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final Attributes getAttributes() {
        return attributes;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Boolean getGiant() {
        return giant;
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
    public final Boolean getMvp() {
        return mvp;
    }

    @Override
    public final String getName() {
        return name;
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
        return Objects.hash(templateName, name);
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
            checkArgument(ability instanceof PersistentAbility,
                    "All the abilities should be an instanceof JPAAbility");

            getAbilitiesModifiable().add((PersistentAbility) ability);
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
        checkArgument(attrs instanceof PersistentAttributes,
                "The Attributes should be an instanceof JPAAttributes");

        attributes = (PersistentAttributes) attrs;
    }

    /**
     * Sets the unit's cost.
     * 
     * @param costUnit
     *            the unit's cost
     */
    public final void setCost(final Integer costUnit) {
        checkNotNull(costUnit, "Received a null pointer as cost");

        cost = costUnit;
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
     * Sets the unit name.
     * 
     * @param unitName
     *            the unit name
     */
    public final void setName(final String unitName) {
        name = unitName;
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
    private final Collection<PersistentAbility> getAbilitiesModifiable() {
        return abilities;
    }

}
