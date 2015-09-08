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
package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPASponsorAssetsAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorAssetsAvailabilityTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPASponsorAssetsAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private SponsorAssetsAvailabilityTestRepository repository;

    public ITJPASponsorAssetsAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPASponsorAssetsAvailability ava;

        ava = repository.findById(1);

        Assert.assertEquals(ava.getAffinityGroupCost(), (Integer) 2);
        Assert.assertEquals(ava.getCheerleaderCost(), (Integer) 3);
        Assert.assertEquals(ava.getCheerleaderUnlockCost(), (Integer) 4);
        Assert.assertEquals(ava.getCoachingDieCost(), (Integer) 5);
        Assert.assertEquals(ava.getMediBotCost(), (Integer) 6);
        Assert.assertEquals(ava.getSabotageCardCost(), (Integer) 7);
        Assert.assertEquals(ava.getSpecialMovementCardCost(), (Integer) 8);
        Assert.assertEquals(ava.getWagerCost(), (Integer) 9);
        Assert.assertEquals(ava.getWagerMaxCount(), (Integer) 10);
        Assert.assertEquals(ava.getTeamCostMin(), (Integer) 11);
    }

}
