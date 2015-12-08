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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.unit.component.ComponentLocation;

/**
 * Persistent JPA-based implementation of {@link ComponentLocation}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "ComponentLocation")
@Table(name = "component_locations")
public final class JPAComponentLocation
        implements ComponentLocation, PersistenceEntity, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -7589272908184471999L;
    /**
     * Component location's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    /**
     * Component location name.
     */
    @Column(name = "name", unique = true)
    private String            name             = "";

    /**
     * Constructs a {@code JPAComponentLocation}.
     */
    public JPAComponentLocation() {
        super();
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    /**
     * Sets the component location name.
     * 
     * @param componentName
     *            the component location name
     */
    public final void setComponentLocationName(final String componentName) {
        checkNotNull(componentName, "Received a null pointer as name");

        name = componentName;
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

}
