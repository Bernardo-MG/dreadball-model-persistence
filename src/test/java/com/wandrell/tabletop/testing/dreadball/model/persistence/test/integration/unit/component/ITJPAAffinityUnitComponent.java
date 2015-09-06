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

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAAffinityUnitComponent;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AffinityUnitComponentTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAAffinityUnitComponent
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AffinityUnitComponentTestRepository repository;

    public ITJPAAffinityUnitComponent() {
        super();
    }

    @Test
    public void test_loads() {
        final JPAAffinityUnitComponent component;

        component = repository.findById(1);

        Assert.assertEquals(component.getId(), (Integer) 1);
        Assert.assertEquals(component.getComponentName(), "component1");

        Assert.assertEquals(component.getAttributes().getArmor(), (Integer) 2);
        Assert.assertEquals(component.getAttributes().getMovement(),
                (Integer) 3);
        Assert.assertEquals(component.getAttributes().getSkill(), (Integer) 4);
        Assert.assertEquals(component.getAttributes().getSpeed(), (Integer) 5);
        Assert.assertEquals(component.getAttributes().getStrength(),
                (Integer) 6);

        Assert.assertEquals(component.getTeamPositions().size(), 1);

        Assert.assertEquals(component.getAbilities().size(), 0);

        Assert.assertEquals(component.getAllyCost(), (Integer) 10);
        Assert.assertEquals(component.getFriendCost(), (Integer) 20);
        Assert.assertEquals(component.getStrangerCost(), (Integer) 30);
    }

    @Test
    public void test_loads_multiplePositions() {
        final JPAAffinityUnitComponent component;

        component = repository.findById(2);

        Assert.assertEquals(component.getId(), (Integer) 2);
        Assert.assertEquals(component.getComponentName(), "component2");

        Assert.assertEquals(component.getAttributes().getArmor(), (Integer) 2);
        Assert.assertEquals(component.getAttributes().getMovement(),
                (Integer) 3);
        Assert.assertEquals(component.getAttributes().getSkill(), (Integer) 4);
        Assert.assertEquals(component.getAttributes().getSpeed(), (Integer) 5);
        Assert.assertEquals(component.getAttributes().getStrength(),
                (Integer) 6);

        Assert.assertEquals(component.getTeamPositions().size(), 2);

        Assert.assertEquals(component.getAbilities().size(), 1);

        Assert.assertEquals(component.getAllyCost(), (Integer) 10);
        Assert.assertEquals(component.getFriendCost(), (Integer) 20);
        Assert.assertEquals(component.getStrangerCost(), (Integer) 30);
    }

}
