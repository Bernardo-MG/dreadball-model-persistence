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
