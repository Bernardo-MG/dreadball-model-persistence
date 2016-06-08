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

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAComponent;
import com.wandrell.tabletop.dreadball.model.unit.AdvancementUnit;
import com.wandrell.tabletop.dreadball.model.unit.component.Component;

/**
 * Abstract persistent JPA-based implementation of {@link AdvancementUnit}.
 * 
 * @author Bernardo Martínez Garrido
 */
@MappedSuperclass
public abstract class AbstractJPAAdvancementUnit extends AbstractJPAUnit
        implements AdvancementUnit {

    /**
     * Unit cost.
     */
    @Column(name = "cost")
    private Integer      cost       = 0;

    /**
     * Unit experience.
     */
    @Column(name = "experience")
    private Integer      experience = 0;

    /**
     * Unit grafted implant.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grafted_implant_id")
    private JPAComponent implant;

    /**
     * Unit name.
     */
    @Column(name = "name")
    private String       name       = "";

    /**
     * Unit rank.
     */
    @Column(name = "rank")
    private Integer      rank       = 0;

    /**
     * Constructs a {@code AbstractJPAAdvancementUnit}.
     */
    public AbstractJPAAdvancementUnit() {
        super();
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Component getGraftedImplant() {
        return implant;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Integer getRank() {
        return rank;
    }

    @Override
    public final Integer getUnspentExperience() {
        return experience;
    }

    @Override
    public final Integer getValoration() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Sets the unit cost.
     * 
     * @param costUnit
     *            unit cost
     */
    public final void setCost(final Integer costUnit) {
        checkNotNull(costUnit, "Received a null pointer as cost");

        cost = costUnit;
    }

    @Override
    public final void setGraftedImplant(final Component graft) {
        checkNotNull(graft, "Received a null pointer as implant");
        checkArgument(graft instanceof JPAComponent,
                "The implant should be an instance of JPAComponent");

        implant = (JPAComponent) graft;
    }

    @Override
    public final void setName(final String unitName) {
        name = unitName;
    }

    @Override
    public final void setRank(final Integer rankUnit) {
        checkNotNull(rankUnit, "Received a null pointer as rank");

        rank = rankUnit;
    }

    @Override
    public final void setUnspentExperience(final Integer exp) {
        checkNotNull(exp, "Received a null pointer as experience");

        experience = exp;
    }

}
