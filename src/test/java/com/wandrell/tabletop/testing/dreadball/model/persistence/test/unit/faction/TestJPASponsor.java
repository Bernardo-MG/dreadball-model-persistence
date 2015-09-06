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

public final class TestJPASponsor {

    public TestJPASponsor() {
        super();
    }

    @Test
    public void test_groups_NoRepeat() {
        final JPASponsor sponsor;
        final JPAAffinityGroup group1;
        final JPAAffinityGroup group2;

        sponsor = new JPASponsor();

        group1 = new JPAAffinityGroup();
        group1.setId(1);
        group1.setAffinityGroupName("group1");

        group2 = new JPAAffinityGroup();
        group2.setId(2);
        group2.setAffinityGroupName("group2");

        sponsor.addPlayerGroup(group1);
        sponsor.addPlayerGroup(group2);
        sponsor.addPlayerGroup(group1);

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 2);
    }

}
