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

package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.faction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorTestRepository;

/**
 * Integration tests for {@link JPASponsor}, checking that the JPA configuration
 * is correct.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Sponsors with no affinities are loaded correctly</li>
 * <li>Sponsors with affinities are loaded correctly</li>
 * </ol>
 * <p>
 * These tests make use of a Spring persistence configuration file to initialize
 * a {@link SponsorTestRepository}, which will be used to acquire the tested
 * entity.
 * <p>
 * Said repository will access a temporal testing database, which will be
 * initialized and populated prior to the tests.
 * 
 * @author Bernardo Martínez Garrido
 */
@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPASponsor
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * Repository used to acquire the tested entity.
     */
    @Autowired
    private SponsorTestRepository repository;

    /**
     * Default constructor.
     */
    public ITJPASponsor() {
        super();
    }

    /**
     * Tests that sponsors with no affinities are loaded correctly
     */
    @Test
    public void test_loads_affinities() {
        final JPASponsor sponsor;       // Tested entity

        sponsor = repository.findById(1);

        Assert.assertEquals(sponsor.getId(), (Integer) 1);
        Assert.assertEquals(sponsor.getName(), "sponsor1");

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 2);
    }

    /**
     * Tests that sponsors with affinities are loaded correctly
     */
    @Test
    public void test_loads_noAffinities() {
        final JPASponsor sponsor;       // Tested entity

        sponsor = repository.findById(2);

        Assert.assertEquals(sponsor.getId(), (Integer) 2);
        Assert.assertEquals(sponsor.getName(), "sponsor2");

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 0);
    }

}
