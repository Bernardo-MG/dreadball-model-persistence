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

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Unit which may change and evolve over time, usually between matches.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "AdvancementUnit")
@Table(name = "advancement_units")
public final class PersistentAdvancementUnit
        extends AbstractPersistentAdvancementUnit implements Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -6317901977987115397L;

    /**
     * Default constructor.
     */
    public PersistentAdvancementUnit() {
        super();
    }

}