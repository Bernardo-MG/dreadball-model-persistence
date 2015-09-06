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

import com.wandrell.tabletop.dreadball.model.unit.component.AffinityUnitComponent;

@Entity(name = "AffinityUnitComponent")
@Table(name = "affinity_unit_components")
public final class JPAAffinityUnitComponent extends AbstractJPAUnitComponent
        implements AffinityUnitComponent, Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cost_ally")
    private Integer           costAlly         = 0;
    @Column(name = "cost_friend")
    private Integer           costFriend       = 0;
    @Column(name = "cost_stranger")
    private Integer           costStranger     = 0;

    public JPAAffinityUnitComponent() {
        super();
    }

    @Override
    public final Integer getAllyCost() {
        return costAlly;
    }

    @Override
    public final Integer getCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public final Integer getFriendCost() {
        return costFriend;
    }

    @Override
    public final Integer getStrangerCost() {
        return costStranger;
    }

    public final void setAllyCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costAlly = cost;
    }

    public final void setFriendCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costFriend = cost;
    }

    public final void setStrangerCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costStranger = cost;
    }

}
