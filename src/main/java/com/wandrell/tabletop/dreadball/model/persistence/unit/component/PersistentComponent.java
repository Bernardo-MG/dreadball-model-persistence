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
import javax.persistence.Table;

/**
 * Component used for creating a composite unit.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Martínez Garrido
 */
@Entity(name = "Component")
@Table(name = "unit_components")
public final class PersistentComponent extends AbstractPersistentComponent
        implements Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -5547831116001472121L;

    /**
     * Component cost.
     */
    @Column(name = "cost")
    private Integer           cost             = 0;

    /**
     * Constructs a {@code JPAComponent}.
     */
    public PersistentComponent() {
        super();
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    /**
     * Sets the component cost.
     * 
     * @param costComponent
     *            the component cost
     */
    public final void setCost(final Integer costComponent) {
        checkNotNull(costComponent, "Received a null pointer as cost");

        cost = costComponent;
    }

}
