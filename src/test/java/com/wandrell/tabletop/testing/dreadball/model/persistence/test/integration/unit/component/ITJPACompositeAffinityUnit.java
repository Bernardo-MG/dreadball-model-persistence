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
package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.CompositeAffinityUnitTestRepository;

/**
 * Integration tests for {@link JPACompositeAffinityUnit}, checking that the JPA
 * configuration is correct.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Units with the Striker role position are loaded correctly</li>
 * <li>Units with the Jack role position are loaded correctly</li>
 * <li>Units with the Guard role position are loaded correctly</li>
 * </ol>
 * <p>
 * These tests make use of a Spring persistence configuration file to initialize
 * a {@link CompositeAffinityUnitTestRepository}, which will be used to acquire
 * the tested entity.
 * <p>
 * Said repository will access a temporal testing database, which will be
 * initialized and populated prior to the tests.
 * 
 * @author Bernardo Martínez Garrido
 */
@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPACompositeAffinityUnit
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * Repository used to acquire the tested entity.
     */
    @Autowired
    private CompositeAffinityUnitTestRepository repository;

    /**
     * Default constructor.
     */
    public ITJPACompositeAffinityUnit() {
        super();
    }

    /**
     * Tests that units with the Guard role position are loaded correctly.
     */
    @Test
    public void test_loads_guard() {
        final JPACompositeAffinityUnit unit;    // Tested entity

        unit = repository.findById(3);

        Assert.assertEquals(unit.getId(), (Integer) 3);
        Assert.assertEquals(unit.getTemplateName(), "caffunit3");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.GUARD);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 0);

        Assert.assertEquals(unit.getAffinityGroups().size(), 1);

        Assert.assertEquals(unit.getComponents().size(), 2);
    }

    /**
     * Tests that units with the Jack role position are loaded correctly.
     */
    @Test
    public void test_loads_jack() {
        final JPACompositeAffinityUnit unit;    // Tested entity

        unit = repository.findById(2);

        Assert.assertEquals(unit.getId(), (Integer) 2);
        Assert.assertEquals(unit.getTemplateName(), "caffunit2");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.JACK);

        Assert.assertEquals(unit.isGiant(), (Boolean) true);

        Assert.assertEquals(unit.getAbilities().size(), 2);

        Assert.assertEquals(unit.getAffinityGroups().size(), 1);

        Assert.assertEquals(unit.getComponents().size(), 1);
    }

    /**
     * Tests that units with the Striker role position are loaded correctly.
     */
    @Test
    public void test_loads_striker() {
        final JPACompositeAffinityUnit unit;    // Tested entity

        unit = repository.findById(1);

        Assert.assertEquals(unit.getId(), (Integer) 1);
        Assert.assertEquals(unit.getTemplateName(), "caffunit1");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.STRIKER);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 1);

        Assert.assertEquals(unit.getAffinityGroups().size(), 2);

        Assert.assertEquals(unit.getComponents().size(), 1);
    }

}
