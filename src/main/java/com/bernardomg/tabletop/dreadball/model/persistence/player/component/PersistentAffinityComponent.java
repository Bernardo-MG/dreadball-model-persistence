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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.player.component.AffinityComponent;

/**
 * Component with affinity groups, and various costs which will depend on how
 * many of such affinities are shared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "AffinityComponent")
@Table(name = "AFFINITY_PLAYER_COMPONENTS")
public class PersistentAffinityComponent extends AbstractPersistentComponent
        implements AffinityComponent, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Ally cost.
     */
    @Column(name = "ally_cost")
    private Integer           allyCost         = 0;

    /**
     * Friend cost.
     */
    @Column(name = "friend_cost")
    private Integer           friendCost       = 0;

    /**
     * Stranger cost.
     */
    @Column(name = "stranger_cost")
    private Integer           strangerCost     = 0;

    /**
     * Default constructor.
     */
    public PersistentAffinityComponent() {
        super();
    }

    @Override
    public Integer getAllyCost() {
        return allyCost;
    }

    @Override
    public Integer getCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer getFriendCost() {
        return friendCost;
    }

    @Override
    public Integer getStrangerCost() {
        return strangerCost;
    }

    /**
     * Sets the ally cost.
     * 
     * @param cost
     *            the ally cost
     */
    public void setAllyCost(final Integer cost) {
        allyCost = cost;
    }

    /**
     * Sets the friend cost.
     * 
     * @param cost
     *            the friend cost
     */
    public void setFriendCost(final Integer cost) {
        friendCost = cost;
    }

    /**
     * Sets the stranger cost.
     * 
     * @param cost
     *            the stranger cost
     */
    public void setStrangerCost(final Integer cost) {
        strangerCost = cost;
    }

}
