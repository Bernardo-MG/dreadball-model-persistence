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
package com.wandrell.tabletop.testing.dreadball.model.persistence.test.unit.faction;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;

/**
 * Unit tests for {@link JPASponsor}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Repeated affinity groups received through the constructor are not
 * repeated</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 */
public final class TestJPASponsor {

    /**
     * Default constructor.
     */
    public TestJPASponsor() {
        super();
    }

    /**
     * Tests that repeated affinity groups received through the constructor are
     * not repeated.
     */
    @Test
    public void test_affinities_NoRepeat() {
        final JPASponsor sponsor;       // Tested sponsor
        final JPAAffinityGroup group1;  // Mocked affinity group
        final JPAAffinityGroup group2;  // Mocked affinity group

        sponsor = new JPASponsor();

        group1 = new JPAAffinityGroup();
        group1.setId(1);
        group1.setAffinityGroupName("group1");

        group2 = new JPAAffinityGroup();
        group2.setId(2);
        group2.setAffinityGroupName("group2");

        sponsor.addAffinityGroup(group1);
        sponsor.addAffinityGroup(group2);
        sponsor.addAffinityGroup(group1);

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 2);
    }

}
