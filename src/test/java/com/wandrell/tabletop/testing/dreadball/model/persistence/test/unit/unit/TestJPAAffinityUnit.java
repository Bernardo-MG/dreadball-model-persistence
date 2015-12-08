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

package com.wandrell.tabletop.testing.dreadball.model.persistence.test.unit.unit;

import java.util.Collection;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityUnit;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

/**
 * Unit tests for {@link JPAAffinityUnit}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Repeated abilities received through the constructor are not repeated</li>
 * <li>Repeated affinities received through the constructor are not repeated
 * </li>
 * <li>Repeated hated affinities received through the constructor are not
 * repeated</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 */
public final class TestJPAAffinityUnit {

    /**
     * Default constructor.
     */
    public TestJPAAffinityUnit() {
        super();
    }

    /**
     * Tests that repeated abilities received through the constructor are not
     * repeated.
     */
    @Test
    public final void test_RepeatAbility_NoRepeats_Constructor() {
        final JPAAffinityUnit unit;                     // Tested unit
        final Collection<Ability> abilities; // Initial abilities
        final Ability ability;               // Mocked ability

        ability = new JPAAbility();
        abilities = new LinkedList<Ability>();
        abilities.add(ability);
        abilities.add(ability);

        unit = new JPAAffinityUnit();

        unit.setAbilities(abilities);
        ;

        Assert.assertEquals(unit.getAbilities().size(), 1);
    }

    /**
     * Tests that repeated affinities received through the constructor are not
     * repeated.
     */
    @Test
    public final void test_RepeatAffinity_NoRepeats_Constructor() {
        final JPAAffinityUnit unit;                    // Tested unit
        final Collection<AffinityGroup> affinities; // Initial affinities
        final AffinityGroup affinity;               // Mocked ability

        affinity = new JPAAffinityGroup();
        affinities = new LinkedList<AffinityGroup>();
        affinities.add(affinity);
        affinities.add(affinity);

        unit = new JPAAffinityUnit();

        unit.setAffinityGroups(affinities);

        Assert.assertEquals(unit.getAffinityGroups().size(), 1);
    }

    /**
     * Tests that repeated hated affinities received through the constructor are
     * not repeated.
     */
    @Test
    public final void test_RepeatHatedAffinity_NoRepeats_Constructor() {
        final JPAAffinityUnit unit;                    // Tested unit
        final Collection<AffinityGroup> affinities; // Initial affinities
        final AffinityGroup affinity;               // Mocked ability

        affinity = new JPAAffinityGroup();
        affinities = new LinkedList<AffinityGroup>();
        affinities.add(affinity);
        affinities.add(affinity);

        unit = new JPAAffinityUnit();

        unit.setHatedAffinityGroups(affinities);

        Assert.assertEquals(unit.getHatedAffinityGroups().size(), 1);
    }

}
