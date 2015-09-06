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
package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPASponsorAffinityGroupAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorAffinityGroupAvailabilityRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPASponsorAffinityGroupAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private SponsorAffinityGroupAvailabilityRepository repository;

    public ITJPASponsorAffinityGroupAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPASponsorAffinityGroupAvailability ava;

        ava = repository.findById(1);

        Assert.assertEquals(ava.getName(), "A");
        Assert.assertEquals(ava.getAffinityGroups().size(), 2);
        Assert.assertEquals(ava.isIncludingRankIncrease(), (Boolean) false);
    }

}
