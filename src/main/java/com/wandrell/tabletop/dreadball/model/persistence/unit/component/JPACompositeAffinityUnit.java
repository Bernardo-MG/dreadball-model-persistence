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

import com.wandrell.tabletop.dreadball.model.persistence.unit.AbstractJPAAffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.component.CompositeAffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.component.UnitComponent;

@Entity(name = "CompositeAffinityUnit")
@Table(name = "composite_affinity_units")
public final class JPACompositeAffinityUnit extends AbstractJPAAffinityUnit
        implements CompositeAffinityUnit, Serializable {

    private static final long                  serialVersionUID = -5866596776570200158L;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "composite_unit_components",
            joinColumns = { @JoinColumn(name = "unit_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "component_id",
                    referencedColumnName = "id") })
    private final Collection<JPAUnitComponent> components       = new LinkedHashSet<JPAUnitComponent>();

    public JPACompositeAffinityUnit() {
        super();
    }

    public final void addComponent(final JPAUnitComponent component) {
        checkNotNull(component, "Received a null pointer as component");

        getComponentsModifiable().add(component);
    }

    @Override
    public final Collection<UnitComponent> getComponents() {
        final Collection<UnitComponent> col;

        col = new LinkedList<>();
        for (final UnitComponent ability : getComponentsModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    public final void removeComponent(final JPAUnitComponent component) {
        getComponentsModifiable().remove(component);
    }

    public final void
            setComponents(final Collection<UnitComponent> components) {
        checkNotNull(components, "Received a null pointer as components");

        getComponentsModifiable().clear();

        for (final UnitComponent component : components) {
            checkArgument(component instanceof JPAUnitComponent,
                    "All the components should be an instanceof JPAUnitComponent");

            getComponentsModifiable().add((JPAUnitComponent) component);
        }
    }

    private final Collection<JPAUnitComponent> getComponentsModifiable() {
        return components;
    }

}
