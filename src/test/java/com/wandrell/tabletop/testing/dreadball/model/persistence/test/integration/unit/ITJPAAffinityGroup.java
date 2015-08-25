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
