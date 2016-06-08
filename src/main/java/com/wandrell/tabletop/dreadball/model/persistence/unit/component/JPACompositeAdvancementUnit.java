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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wandrell.tabletop.dreadball.model.persistence.unit.AbstractJPAAdvancementUnit;
import com.wandrell.tabletop.dreadball.model.unit.component.Component;
import com.wandrell.tabletop.dreadball.model.unit.component.CompositeAdvancementUnit;

/**
 * Persistent JPA-based implementation of {@link CompositeAdvancementUnit}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "CompositeAdvancementUnit")
@Table(name = "composite_advancement_units")
public final class JPACompositeAdvancementUnit
        extends AbstractJPAAdvancementUnit
        implements CompositeAdvancementUnit, Serializable {

    /**
     * Serialization ID.
     */
    private static final long              serialVersionUID = 4751347276326003773L;

    /**
     * Unit components.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "composite_unit_components",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "component_id",
                    referencedColumnName = "id") })
    private final Collection<JPAComponent> components       = new LinkedHashSet<JPAComponent>();

    /**
     * Constructs a {@code JPACompositeAdvancementUnit}.
     */
    public JPACompositeAdvancementUnit() {
        super();
    }

    /**
     * Adds a component.
     * 
     * @param component
     *            the component to add
     */
    public final void addComponent(final JPAComponent component) {
        checkNotNull(component, "Received a null pointer as component");

        getComponentsModifiable().add(component);
    }

    @Override
    public final Collection<Component> getComponents() {
        final Collection<Component> col;

        col = new LinkedList<>();
        for (final Component ability : getComponentsModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    /**
     * Removes a component.
     * 
     * @param component
     *            the component to remove
     */
    public final void removeComponent(final JPAComponent component) {
        getComponentsModifiable().remove(component);
    }

    /**
     * Sets the unit components.
     * <p>
     * If the unit has any component these are removed and swapped with the
     * received ones.
     * 
     * @param unitComponents
     *            the components to set on the unit
     */
    public final void
            setComponents(final Collection<Component> unitComponents) {
        checkNotNull(unitComponents, "Received a null pointer as components");

        getComponentsModifiable().clear();

        for (final Component component : unitComponents) {
            checkArgument(component instanceof JPAComponent,
                    "All the components should be an instanceof JPAComponent");

            getComponentsModifiable().add((JPAComponent) component);
        }
    }

    /**
     * Returns the modifiable collection of the unit's components.
     * 
     * @return the modifiable collection of the unit's components
     */
    private final Collection<JPAComponent> getComponentsModifiable() {
        return components;
    }

}
