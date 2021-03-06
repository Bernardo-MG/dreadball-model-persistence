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

package com.bernardomg.tabletop.dreadball.model.persistence.player.stats;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.player.stats.AffinityGroup;
import com.google.common.base.MoreObjects;

/**
 * Affinity group.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "AffinityGroup")
@Table(name = "AFFINITY_GROUPS")
public class PersistentAffinityGroup implements AffinityGroup, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 3702432119601675635L;

    /**
     * Affinity group's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;

    /**
     * Affinity group¡s name.
     */
    @Column(name = "name", unique = true)
    private String            name             = "";

    /**
     * Default constructor.
     */
    public PersistentAffinityGroup() {
        super();
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

        final PersistentAffinityGroup other;

        other = (PersistentAffinityGroup) obj;
        return Objects.equals(name, other.name);
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
    public String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    /**
     * Sets the affinity group's name.
     * 
     * @param nameAffinity
     *            the affinity group's name
     */
    public void setAffinityGroupName(final String nameAffinity) {
        name = nameAffinity;
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

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
