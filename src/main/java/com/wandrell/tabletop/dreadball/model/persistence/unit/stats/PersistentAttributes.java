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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.wandrell.tabletop.dreadball.model.unit.stats.Attributes;

/**
 * Interface representing unit attributes.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Embeddable
public final class PersistentAttributes implements Attributes, Serializable {

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
     * Default constructor.
     */
    public PersistentAttributes() {
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
        final PersistentAttributes other = (PersistentAttributes) obj;
        return Objects.equals(armor, other.armor)
                && Objects.equals(movement, other.movement)
                && Objects.equals(skill, other.skill)
                && Objects.equals(speed, other.speed)
                && Objects.equals(strength, other.strength);
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

    @Override
    public final int hashCode() {
        return Objects.hash(armor, movement, skill, speed, strength);
    }

    /**
     * Sets the armor value.
     * 
     * @param armorValue
     *            the armor value
     */
    public final void setArmor(final Integer armorValue) {
        checkNotNull(armorValue, "Received a null pointer as armor");

        armor = armorValue;
    }

    /**
     * Sets the movement value.
     * 
     * @param movementValue
     *            the movement value
     */
    public final void setMovement(final Integer movementValue) {
        checkNotNull(movementValue, "Received a null pointer as movement");

        movement = movementValue;
    }

    /**
     * Sets the skill value.
     * 
     * @param skillValue
     *            the skill value
     */
    public final void setSkill(final Integer skillValue) {
        checkNotNull(skillValue, "Received a null pointer as skill");

        skill = skillValue;
    }

    /**
     * Sets the speed value.
     * 
     * @param speedValue
     *            the speed value
     */
    public final void setSpeed(final Integer speedValue) {
        checkNotNull(speedValue, "Received a null pointer as speed");

        speed = speedValue;
    }

    /**
     * Sets the strength value.
     * 
     * @param strengthValue
     *            the strength value
     */
    public final void setStrength(final Integer strengthValue) {
        checkNotNull(strengthValue, "Received a null pointer as strength");

        strength = strengthValue;
    }

}
