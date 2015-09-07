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
package com.wandrell.tabletop.dreadball.model.persistence.unit.stats;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.wandrell.tabletop.dreadball.model.unit.stats.AttributesHolder;

/**
 * Persistent JPA-based embeddable implementation of {@link AttributesHolder}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Embeddable
public final class JPAAttributesHolder
        implements AttributesHolder, Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Armor value.
     */
    @Column(name = "armor")
    private Integer           armor            = 0;
    /**
     * Movement value.
     */
    @Column(name = "movement")
    private Integer           movement         = 0;
    /**
     * Skill value.
     */
    @Column(name = "skill")
    private Integer           skill            = 0;
    /**
     * Speed value.
     */
    @Column(name = "speed")
    private Integer           speed            = 0;
    /**
     * Strength value.
     */
    @Column(name = "strength")
    private Integer           strength         = 0;

    /**
     * Constructs a {@code JPAAttributesHolder}.
     */
    public JPAAttributesHolder() {
        super();
    }

    @Override
    public final Integer getArmor() {
        return armor;
    }

    @Override
    public final Integer getMovement() {
        return movement;
    }

    @Override
    public final Integer getSkill() {
        return skill;
    }

    @Override
    public final Integer getSpeed() {
        return speed;
    }

    @Override
    public final Integer getStrength() {
        return strength;
    }

    /**
     * Sets the armor value.
     * 
     * @param armor
     *            the armor value
     */
    public final void setArmor(final Integer armor) {
        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor;
    }

    /**
     * Sets the movement value.
     * 
     * @param movement
     *            the movement value
     */
    public final void setMovement(final Integer movement) {
        checkNotNull(movement, "Received a null pointer as movement");

        this.movement = movement;
    }

    /**
     * Sets the skill value.
     * 
     * @param skill
     *            the skill value
     */
    public final void setSkill(final Integer skill) {
        checkNotNull(skill, "Received a null pointer as skill");

        this.skill = skill;
    }

    /**
     * Sets the speed value.
     * 
     * @param speed
     *            the speed value
     */
    public final void setSpeed(final Integer speed) {
        checkNotNull(speed, "Received a null pointer as speed");

        this.speed = speed;
    }

    /**
     * Sets the strength value.
     * 
     * @param strength
     *            the strength value
     */
    public final void setStrength(final Integer strength) {
        checkNotNull(strength, "Received a null pointer as strength");

        this.strength = strength;
    }

}
