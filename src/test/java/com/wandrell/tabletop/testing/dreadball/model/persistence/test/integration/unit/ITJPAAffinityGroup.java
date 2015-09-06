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
package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AffinityGroupTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAAffinityGroup
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AffinityGroupTestRepository repository;

    public ITJPAAffinityGroup() {
        super();
    }

    @Test
    public void test_loads() {
        final JPAAffinityGroup group;

        group = repository.findById(2);

        Assert.assertEquals(group.getId(), (Integer) 2);
        Assert.assertEquals(group.getAffinityGroupName(), "group2");
    }

}
